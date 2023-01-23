package camandedit.server.user.controller;

import camandedit.server.global.response.JsonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {


    @GetMapping("/api/auth/check")
    public ResponseEntity<?> checkToken() {
        return JsonResponse.ok(HttpStatus.OK, "인증 성공");
    }
}
