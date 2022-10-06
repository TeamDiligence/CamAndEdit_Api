package camandedit.server.workspace.controller;

import camandedit.server.global.config.web.JwtAuthUser;
import camandedit.server.global.config.web.LoginUser;
import camandedit.server.global.response.JsonResponse;
import camandedit.server.workspace.application.WorkSpaceFacade;
import camandedit.server.workspace.application.command.CreateWorkSpaceCommand;
import camandedit.server.workspace.application.dto.WorkSpaceMemberListResponse;
import camandedit.server.workspace.application.dto.WorkSpaceResponse;
import camandedit.server.workspace.controller.dto.CheckInviteRequest;
import camandedit.server.workspace.controller.dto.InviteWorkSpaceRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class WorkSpaceController {

  private final WorkSpaceFacade workSpaceFacade;

  @PostMapping("/api/workspace")
  public ResponseEntity<?> createWorkSpace(
      @RequestParam(required = false) String name,
      @RequestParam("file") MultipartFile file,
      @LoginUser JwtAuthUser user) {
    workSpaceFacade.create(new CreateWorkSpaceCommand(user.getUserId(), name, file));
    return JsonResponse.ok(HttpStatus.OK, "워크스페이스 생성 성공");
  }

  @GetMapping("/api/workspace/my")
  public ResponseEntity<?> getMyWorkSpace(@LoginUser JwtAuthUser user) {
    List<WorkSpaceResponse> result = workSpaceFacade.getMyWorkSpaceList(user.getUserId());
    return JsonResponse.okWithData(HttpStatus.OK, "내 워크스페이스 조회 성공", result);
  }

  @GetMapping("/api/workspace/{id}/members")
  public ResponseEntity<?> getMemberList(
      @PathVariable("id") Long workSpaceId,
      @LoginUser JwtAuthUser user) {
    WorkSpaceMemberListResponse result = workSpaceFacade.getMemberList(workSpaceId,
        user.getUserId());
    return JsonResponse.okWithData(HttpStatus.OK, "워크스페이스 사용자 조회 성공", result);
  }


  @PostMapping("/api/workspace/{id}/invite")
  public ResponseEntity<?> inviteWorkSpace(
      @LoginUser JwtAuthUser user,
      @RequestBody InviteWorkSpaceRequest requestDto,
      @PathVariable("id") Long workSpaceId) {
    workSpaceFacade.invite(requestDto.toCommand(user.getUserId(), workSpaceId));
    return JsonResponse.ok(HttpStatus.OK, "초대 메일 전송 성공");
  }

  @PostMapping("/api/workspace/{id}/invite/check")
  public ResponseEntity<?> checkInvite(
      @LoginUser JwtAuthUser user,
      @RequestBody CheckInviteRequest requestDto,
      @PathVariable("id") Long workSpaceId
  ){
    workSpaceFacade.checkInvite(requestDto.toCommand(user.getUserId(), workSpaceId));
    return JsonResponse.ok(HttpStatus.OK, "초대 확인 성공");
  }


  @GetMapping("/api/workspace/{id}")
  public ResponseEntity<?> getWorkSpaceDetail(@PathVariable("id") Long workSpaceId) {
    WorkSpaceResponse result = workSpaceFacade.getDetailInfo(workSpaceId);
    return JsonResponse.okWithData(HttpStatus.OK, "워크스페이스 조회 성공", result);
  }
}
