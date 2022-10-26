package camandedit.server.cam.controller;

import camandedit.server.cam.application.MeetingRoomPubSubUseCase;
import camandedit.server.cam.application.PublisherService;
import camandedit.server.cam.application.WorkSpacePubSubUseCase;
import camandedit.server.global.config.socket.SocketUser;
import camandedit.server.global.exception.BusinessException;
import camandedit.server.global.exception.InvalidInputException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
  private boolean isWorkSpaceRequest(String type) {
    if (type.equals("WORKSPACE")) {
      return true;
    }
    return false;
  }

  private boolean isMeetingRoomRequest(String type) {
    if (type.equals("ROOM")) {
      return true;
    }
    return false;
  }

  private Long parseId(StompHeaderAccessor accessor) {
    String id = accessor.getFirstNativeHeader("id");
    if (id == null) {
      throw new InvalidInputException("header에 id가 없습니다.");
    }
    return Long.valueOf(id);
  }


  @Override
  public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
    StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message,
        StompHeaderAccessor.class);
    SocketUser simpUser = (SocketUser) accessor.getHeader("simpUser");
    StompCommand command = accessor.getCommand();
    String sessionId = accessor.getSessionId();
    String destination = accessor.getDestination();

    String type = accessor.getFirstNativeHeader("type");
    if (command != null) {
      log.info("[커맨드] = " + command + " [URL] " + destination + " TYPE = " + type + " Session = "
          + sessionId);
    }
    try {
      if (command != null && command.equals(StompCommand.SUBSCRIBE)) {
        if (isWorkSpaceRequest(type)) {
          Long workSpaceId = parseId(accessor);
          log.info("워크스페이스 ID = " + workSpaceId);
          workSpacePubSubUseCase.subscribeWorkSpace(workSpaceId, simpUser.getUserId(), sessionId);
        }
        if (isMeetingRoomRequest(type)) {
          Long roomId = parseId(accessor);
          log.info("ROOM ID = " + roomId);
          meetingRoomPubSubUseCase.subscribeRoom(roomId, sessionId);
        }
      } else if (command != null && command.equals(StompCommand.UNSUBSCRIBE)) {
        if (isWorkSpaceRequest(type)) {
          workSpacePubSubUseCase.unSubscribeWorkSpace(sessionId);
        }

        if (isMeetingRoomRequest(type)) {
          Long roomId = parseId(accessor);
          log.info("ROOM ID = " + roomId);
          ;
          meetingRoomPubSubUseCase.unSubscribeMeetingRoom(roomId, sessionId);
        }
      } else if (command != null && command.equals(StompCommand.DISCONNECT)) {
        log.info("[Disconnect] : session = " + sessionId);
        workSpacePubSubUseCase.disconnectCleanUp(sessionId);
      }
    } catch (BusinessException e) {
      log.error("ERROR 발생 " + e.getMessage());
      publisherService.sendErrorToUser(sessionId, e.getErrorType(), e.getMessage());
    }
  }
}
