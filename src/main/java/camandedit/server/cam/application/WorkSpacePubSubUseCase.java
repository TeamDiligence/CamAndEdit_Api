package camandedit.server.cam.application;

import camandedit.server.cam.domain.CamMeetingRoom;
import camandedit.server.cam.domain.ConnectUser;
import camandedit.server.cam.infra.CamMeetingRoomRepository;
import camandedit.server.cam.infra.ConnectUserRepository;
import camandedit.server.global.exception.NotFoundResourceException;
import camandedit.server.workspace.domain.WorkSpaceMember;
import camandedit.server.workspace.domain.repository.WorkSpaceRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkSpacePubSubUseCase {

  private final ConnectUserRepository connectUserRepository;
  private final WorkSpaceRepository workSpaceRepository;
  private final CamMeetingRoomRepository camMeetingRoomRepository;
  private final PublisherService publisherService;
  /*
   *  워크스페이스 구독 시 Redis에 STOMP 세션 key : 유저 정보로 매핑해서 저장한다.
   */

  @Transactional
  public void subscribeWorkSpace(Long workSpaceId, Long userId, String sessionId) {
    WorkSpaceMember member = workSpaceRepository.findMember(workSpaceId, userId);
    connectUserRepository.save(ConnectUser.builder()
        .userImage(member.getUser().getUserImage())
        .sessionId(sessionId)
        .userId(userId)
        .workSpaceId(workSpaceId)
        .nickname(member.getNickname())
        .build());
    publisherService.sendMeetingRoomCurrentUser(workSpaceId);
  }

  @Transactional
  public void unSubscribeWorkSpace(String sessionId) {
    ConnectUser connectUser = connectUserRepository.findById(sessionId)
        .orElseThrow(() -> new NotFoundResourceException("레디스 정보를 찾을 수 없음"));

    if (connectUser.isSubscribeMeetingRoom()) {
      removeUserParticipationInfo(connectUser, sessionId);
    }

    connectUserRepository.deleteById(sessionId);
    publisherService.sendMeetingRoomCurrentUser(connectUser.getWorkSpaceId());
  }

  @Transactional
  public void disconnectCleanUp(String sessionId) {
    Optional<ConnectUser> connectUser = connectUserRepository.findById(sessionId);

    if (connectUser.isPresent()) {
      if (connectUser.get().isSubscribeMeetingRoom()) {
        removeUserParticipationInfo(connectUser.get(), sessionId);
      }
      connectUserRepository.deleteById(sessionId);
      publisherService.sendMeetingRoomCurrentUser(connectUser.get().getWorkSpaceId());
    }
  }

  private void removeUserParticipationInfo(ConnectUser connectUser, String sessionId) {
    CamMeetingRoom camMeetingRoom = camMeetingRoomRepository.findById(
            connectUser.getMeetingRoomId())
        .orElseThrow(() -> new NotFoundResourceException("해당 미팅룸을 찾을 수 없습니다."));
    System.out.println("[클린 업!!]" + camMeetingRoom.toString());
    camMeetingRoom.removeUser(sessionId);
    camMeetingRoomRepository.save(camMeetingRoom);
  }

}
