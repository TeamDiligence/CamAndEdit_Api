package camandedit.server.chat.controller;

import camandedit.server.chat.application.ChatUseCase;
import camandedit.server.chat.controller.dto.CreateWorkSpaceChatRequest;
import camandedit.server.global.config.web.JwtAuthUser;
import camandedit.server.global.config.web.LoginUser;
import camandedit.server.global.response.JsonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ChatHttpController {

    private final ChatUseCase chatUseCase;

    @GetMapping("/api/chat/workspace")
    public ResponseEntity<?> getChattingByWorkSpace() {

        return JsonResponse.ok(HttpStatus.OK, "");
    }

    @PostMapping("/api/chat/workspace")
    public ResponseEntity<?> chatWorkSpace(@LoginUser JwtAuthUser user,
        @RequestBody CreateWorkSpaceChatRequest dto) {
        chatUseCase.createChat(dto.toCommand(user.getUserId()));
        return JsonResponse.ok(HttpStatus.OK, "채팅 생성 성공");
    }


}
