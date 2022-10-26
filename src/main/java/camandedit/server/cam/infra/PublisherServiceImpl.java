package camandedit.server.cam.infra;

import camandedit.server.cam.application.PublisherService;
import camandedit.server.cam.controller.dto.RoomCurrentUserDto;
import camandedit.server.cam.domain.CamMeetingRoom;
import camandedit.server.cam.application.dto.RoomCurrentUserResponse;
import camandedit.server.global.exception.ErrorType;
import camandedit.server.global.response.SocketErrorResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

  private final RedisTemplate redisTemplate;
  private final CamMeetingRoomRepository camMeetingRoomRepository;

  @Override
  public void sendMeetingRoomCurrentUser(Long workSpaceId) {
    List<CamMeetingRoom> camMeetingRooms = camMeetingRoomRepository.findAllByWorkspaceId(
        workSpaceId);
    List<RoomCurrentUserResponse> currentUser = camMeetingRooms.stream()
        .map(RoomCurrentUserResponse::from).toList();

    if (!currentUser.isEmpty()) {
      redisTemplate.convertAndSend(ChannelTopic.of("/workspace").getTopic(),
          new RoomCurrentUserDto(WorkSpacePublisherType.ROOM_INFO, workSpaceId, currentUser));
    } else {
      redisTemplate.convertAndSend(ChannelTopic.of("/workspace").getTopic(),
          new RoomCurrentUserDto(WorkSpacePublisherType.ROOM_INFO, workSpaceId, new ArrayList<>()));

    }
  }

  @Override
  public void sendErrorToUser(String sessionId, ErrorType errorType, String message) {

    SocketErrorResponse response = new SocketErrorResponse(message, errorType.toString(),
        sessionId);
    redisTemplate.convertAndSend(ChannelTopic.of("/user/error").getTopic(), response);
  }
}
