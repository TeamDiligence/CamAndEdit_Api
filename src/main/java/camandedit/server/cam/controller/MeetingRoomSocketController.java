package camandedit.server.cam.controller;

import camandedit.server.cam.application.GetWorkSpaceMeetingRoomUseCase;
import camandedit.server.cam.controller.dto.ChatDto;
import camandedit.server.cam.controller.dto.WorkSpaceChatDto;
import camandedit.server.cam.domain.ConnectUser;
import camandedit.server.cam.infra.WorkSpacePublisherType;
import camandedit.server.global.config.socket.SocketUser;
import camandedit.server.global.exception.BusinessException;
import camandedit.server.global.exception.ErrorType;
import camandedit.server.global.response.SocketErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MeetingRoomSocketController {

  private final SimpMessageSendingOperations messagingTemplate;
  private final GetWorkSpaceMeetingRoomUseCase getWorkSpaceMeetingRoomUseCase;

  @MessageMapping("/workspace/{workSpaceId}")
  public void test(@Payload String message, @DestinationVariable("workSpaceId") Long id,
      @Header("simpUser") SocketUser user) {

    ConnectUser currentUser = getWorkSpaceMeetingRoomUseCase.getCurrentUser(user.getSessionId());
    messagingTemplate.convertAndSend("/topic/workspace/" + id,
        new WorkSpaceChatDto(WorkSpacePublisherType.CHAT_INFO, id, ChatDto.builder()
            .message(message)
            .nickname(currentUser.getNickname())
            .senderId(user.getUserId())
            .userImage(currentUser.getUserImage()).build()));
  }

  /*
   *  /user/queue/test로 구독 하고, /pub/send/test로 요청을 보낸다.
   */
  @MessageMapping("/send/test")
  @SendToUser("/queue/test")
  public String sendTest() {
    log.info("/send/test 테스트 요청옴");
    return "안녕";
  }

  /*
   *  Error Test용 EndPoint.
   *  /pub/error
   */
  @MessageMapping("/error")
  public void solo(@Payload String message) {
    log.info("받은 값 : " + message);
    throw new BusinessException(ErrorType.INTERNAL_SERVER_ERROR);
  }


  @MessageExceptionHandler
  public void handleException(BusinessException exception, @Header("simpUser") SocketUser user) {
    log.error("에러 발생!");
    System.out.println(user.toString());
    messagingTemplate.convertAndSendToUser(user.getName(), "/user/queue/errors",
        new SocketErrorResponse(exception.getMessage()));
  }
}
