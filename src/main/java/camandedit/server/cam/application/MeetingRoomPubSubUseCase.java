package camandedit.server.cam.application;

import camandedit.server.cam.domain.CamMeetingRoom;
import camandedit.server.cam.domain.ConnectUser;
import camandedit.server.cam.exception.AlreadyPariticipateCamRoomException;
import camandedit.server.cam.infra.CamMeetingRoomRepository;
import camandedit.server.cam.infra.ConnectUserRepository;
import camandedit.server.cam.infra.JpaMeetingRoomRepository;
import camandedit.server.global.exception.NotFoundResourceException;
import camandedit.server.workspace.domain.MeetingRoom;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MeetingRoomPubSubUseCase {

  private final CamMeetingRoomRepository camMeetingRoomRepository;
  private final ConnectUserRepository connectUserRepository;
  private final JpaMeetingRoomRepository jpaMeetingRoomRepository;
  private final PublisherService publisherService;

  @Transactional
  public void subscribeRoom(Long roomId, String sessionId) {

    CamMeetingRoom camMeetingRoom = camMeetingRoomRepository.findById(roomId)
        .orElseGet(() -> init(roomId));
    ConnectUser connectUser = connectUserRepository.findById(sessionId)
        .orElseThrow(() -> new NotFoundResourceException("해당 유저를 찾을 수 없습니다."));
    if (isAlreadyUserMeetingRoom(connectUser.getUserId())) {
      throw new AlreadyPariticipateCamRoomException("이미 다른 미팅룸에 참여중입니다.");
    }
    camMeetingRoom.addUser(connectUser);
    connectUser.subMeetingRoom(roomId);

    camMeetingRoomRepository.save(camMeetingRoom);
    connectUserRepository.save(connectUser);
    publisherService.sendMeetingRoomCurrentUser(connectUser.getWorkSpaceId());
  }

  private boolean isAlreadyUserMeetingRoom(Long userId) {
    List<ConnectUser> allByUserId = connectUserRepository.findAllByUserId(userId);
    return allByUserId.stream().anyMatch(
        user -> user.getMeetingRoomId() != null);
  }


  @Transactional
  public void unSubscribeMeetingRoom(Long roomId, String sessionId) {
    CamMeetingRoom camMeetingRoom = camMeetingRoomRepository.findById(roomId)
        .orElseThrow(() -> new NotFoundResourceException("해당 룸에 대한 세션 정보를 찾을 수 없습니다."));
    ConnectUser connectUser = connectUserRepository.findById(sessionId)
        .orElseThrow(() -> new NotFoundResourceException("해당 유저를 찾을 수 없습니다."));
    camMeetingRoom.removeUser(sessionId);
    connectUser.subSubMeetingRoom();
    camMeetingRoomRepository.save(camMeetingRoom);
    connectUserRepository.save(connectUser);
    publisherService.sendMeetingRoomCurrentUser(camMeetingRoom.getWorkspaceId());
  }

  private CamMeetingRoom init(Long roomId) {
    MeetingRoom meetingRoom = jpaMeetingRoomRepository.findById(roomId)
        .orElseThrow(() -> new NotFoundResourceException("해당 미팅룸을 찾을 수 없습니다."));
    return new CamMeetingRoom(roomId, meetingRoom.getWorkSpace().getId());
  }
}
