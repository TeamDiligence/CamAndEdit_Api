package camandedit.server.user.controller;

import camandedit.server.global.config.web.JwtAuthUser;
import camandedit.server.global.config.web.LoginUser;
import camandedit.server.global.response.JsonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {


  @GetMapping("/api/user")
  public ResponseEntity<?> getUser(@LoginUser JwtAuthUser user) {
    System.out.println("USER ID = "+ user.getUserId());
    return JsonResponse.ok(HttpStatus.OK, "조회 성공");
  }
}
