package camandedit.server.cam.controller;

import camandedit.server.cam.controller.dto.AnswerRequestDto;
import camandedit.server.cam.controller.dto.AnswerResponseDto;
import camandedit.server.cam.controller.dto.CandidateRequestDto;
import camandedit.server.cam.controller.dto.CandidateResponseDto;
import camandedit.server.cam.controller.dto.OfferRequestDto;
import camandedit.server.cam.controller.dto.OfferResponseDto;
import camandedit.server.cam.domain.CamMeetingRoom;
import camandedit.server.cam.domain.ConnectUser;
import camandedit.server.cam.infra.CamMeetingRoomRepository;
import camandedit.server.global.config.socket.SocketUser;
import camandedit.server.global.exception.BusinessException;
import camandedit.server.global.exception.NotFoundResourceException;
import camandedit.server.global.response.SocketErrorResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WebRtcSignalingController {

  private final CamMeetingRoomRepository camMeetingRoomRepository;
  private final SimpMessagingTemplate messagingTemplate;

  @MessageMapping("/offer/{roomId}")
  public void offer(@Payload OfferRequestDto dto, @DestinationVariable("roomId") Long roomId,
      @Header("simpUser") SocketUser user) {
    log.info("[OFFER] " + roomId + " RECEIVE SDP = " + dto.getSdp().toString());
    CamMeetingRoom room = camMeetingRoomRepository.findById(roomId)
        .orElseThrow(() -> new NotFoundResourceException(roomId + " 해당 방을 찾을 수 없습니다."));

    ConnectUser roomUser = room.getUser(dto.getReceiveId());
    OfferResponseDto response = new OfferResponseDto(roomId, user.getUserId(),
        dto.getSdp());
    messagingTemplate.convertAndSendToUser(roomUser.getSessionId(), "/queue/getoffer",
        response);

  }

  @MessageMapping("/answer/{roomId}")
  public void answer(@Payload AnswerRequestDto dto, @DestinationVariable("roomId") Long roomId,
      @Header("simpUser") SocketUser user) {
    log.info("[ANSWER] " + roomId + " ANSWER SDP = " + dto.getAnswerSdp().toString());
    CamMeetingRoom room = camMeetingRoomRepository.findById(roomId)
        .orElseThrow(() -> new NotFoundResourceException(roomId + " 해당 방을 찾을 수 없습니다."));
    ConnectUser originalUser = room.getUser(dto.getOriginalSenderId());

    messagingTemplate.convertAndSendToUser(originalUser.getSessionId(), "/queue/getanswer",
        new AnswerResponseDto(user.getUserId(), roomId, dto.getAnswerSdp()));
  }

  @MessageMapping("/candidate/{roomId}")
  public void candidate(@Payload CandidateRequestDto dto,
      @DestinationVariable("roomId") Long roomId,
      @Header("simpUser") SocketUser user) {
    log.info("[CANDIDATE] " + roomId + " CANDIDATE  = " + dto.getCandidate().toString());
    CamMeetingRoom room = camMeetingRoomRepository.findById(roomId)
        .orElseThrow(() -> new NotFoundResourceException(roomId + " 해당 방을 찾을 수 없습니다."));
    ConnectUser receiver = room.getUser(dto.getReceiverId());

    messagingTemplate.convertAndSendToUser(receiver.getSessionId(), "/queue/getcandidate",
        new CandidateResponseDto(user.getUserId(), roomId, dto.getCandidate()));
  }


  @MessageExceptionHandler
  public void handleException(BusinessException e, @Header("simpUser") SocketUser user) {
    log.error("에러 발생 " + e.getMessage());
    messagingTemplate.convertAndSendToUser(user.getName(), "/queue/errors",
        new SocketErrorResponse(e.getMessage(), e.getErrorType().toString(), user.getSessionId()));
  }
}
