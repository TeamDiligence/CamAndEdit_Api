package camandedit.server.workspace.controller;

import camandedit.server.global.config.web.JwtAuthUser;
import camandedit.server.global.config.web.LoginUser;
import camandedit.server.global.response.JsonResponse;
import camandedit.server.workspace.application.WorkSpaceFacade;
import camandedit.server.workspace.application.command.CreateWorkSpaceCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
}
