package camandedit.server.user.controller;

import camandedit.server.global.config.web.JwtAuthUser;
import camandedit.server.global.config.web.LoginUser;
import camandedit.server.global.response.JsonResponse;
import camandedit.server.user.application.UserFacade;
import camandedit.server.user.application.dto.UserResponse;
import camandedit.server.user.controller.dto.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserFacade userFacade;


  @GetMapping("/api/user")
  public ResponseEntity<?> getUser(@LoginUser JwtAuthUser user) {
    UserResponse userInfo = userFacade.getUserInfo(user.getUserId());
    return JsonResponse.okWithData(HttpStatus.OK, "조회 성공", userInfo);
  }

  @PostMapping("/api/user")
  public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
    userFacade.createUser(request.toCommand());
    return JsonResponse.ok(HttpStatus.CREATED, "가입 성공");
  }
}
