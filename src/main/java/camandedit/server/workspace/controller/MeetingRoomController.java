package camandedit.server.workspace.controller;

import camandedit.server.global.config.web.JwtAuthUser;
import camandedit.server.global.config.web.LoginUser;
import camandedit.server.global.response.JsonResponse;
import camandedit.server.workspace.application.WorkSpaceFacade;
import camandedit.server.workspace.controller.dto.CreateMeetingRoomRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MeetingRoomController {

  private final WorkSpaceFacade workSpaceFacade;

  @PostMapping("/api/meeting-room")
  public ResponseEntity<?> createMeetingRoom(@RequestBody CreateMeetingRoomRequest dto,
      @LoginUser JwtAuthUser user) {
    workSpaceFacade.creatMeetingRoom(dto.toCommand(user.getUserId()));
    return JsonResponse.ok(HttpStatus.CREATED, "미팅룸 생성 성공");
  }
}
