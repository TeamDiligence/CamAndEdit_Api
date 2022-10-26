package camandedit.server.cam.controller;

import camandedit.server.cam.application.MeetingRoomPubSubUseCase;
import camandedit.server.cam.application.PublisherService;
import camandedit.server.cam.application.WorkSpacePubSubUseCase;
import camandedit.server.global.config.socket.SocketUser;
import camandedit.server.global.exception.BusinessException;
import camandedit.server.global.response.SocketErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CamSocketHandler implements ChannelInterceptor {

  private final WorkSpacePubSubUseCase workSpacePubSubUseCase;
  private final MeetingRoomPubSubUseCase meetingRoomPubSubUseCase;
  private final PublisherService publisherService;

  @Override
  public Message<?> preSend(Message<?> message, MessageChannel channel) {
    StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message,
        StompHeaderAccessor.class);

    return message;
  }

  /*
   *  destination 형식
   *  워크스페이스 : /topic/workspace/3
   *  미팅 룸 : /topic/meetingroom/2
   *
   */
  private boolean isWorkSpaceRequest(String destination) {
    if (destination.trim().startsWith("/topic/workspace/")) {
      return true;
    }
    return false;
  }

  private boolean isMeetingRoomRequest(String destincation) {
    if (destincation.trim().startsWith("/topic/meetingroom/")) {
      return true;
    }
    return false;
  }


  private Long getId(String destination) {
    int index = destination.lastIndexOf("/");
    String substring = destination.substring(index + 1);
    return Long.parseLong(substring);
  }


  @Override
  public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
    StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message,
        StompHeaderAccessor.class);
    SocketUser simpUser = (SocketUser) accessor.getHeader("simpUser");
    System.out.println("유저? = " + simpUser.toString());
    StompCommand command = accessor.getCommand();
    String sessionId = accessor.getSessionId();
    String destination = accessor.getDestination();

    log.info("[커맨드] = " + command + " [URL]" + destination + " Session = " + sessionId);
    try {
      if (command.equals(StompCommand.SUBSCRIBE)) {
        if (isWorkSpaceRequest(destination)) {
          Long workSpaceId = getId(destination);
          workSpacePubSubUseCase.subscribeWorkSpace(workSpaceId, simpUser.getUserId(), sessionId);
        }
        if (isMeetingRoomRequest(destination)) {
          Long roomId = getId(destination);
          meetingRoomPubSubUseCase.subscribeRoom(roomId, sessionId);
        }
      } else if (command.equals(StompCommand.UNSUBSCRIBE)) {
        log.info("[구독 취소]" + "Destination = " + destination + " session = " + sessionId);
        if (isWorkSpaceRequest(destination)) {
          workSpacePubSubUseCase.unSubscribeWorkSpace(sessionId);
        }

        if (isMeetingRoomRequest(destination)) {
          Long roomId = getId(destination);
          meetingRoomPubSubUseCase.unSubscribeMeetingRoom(roomId, sessionId);
        }

      } else if (command.equals(StompCommand.DISCONNECT)) {
        log.info("[Disconnect] : session = " + sessionId);
        workSpacePubSubUseCase.disconnectCleanUp(sessionId);
      }
    } catch (BusinessException e) {
      publisherService.sendErrorToUser(sessionId, e.getErrorType(), e.getMessage());
    }
  }
}
