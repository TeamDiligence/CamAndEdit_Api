package camandedit.server.chat.controller;

import camandedit.server.chat.application.ChatUseCase;
import camandedit.server.chat.application.dto.WorkSpaceChatResponse;
import camandedit.server.chat.controller.dto.CreateWorkSpaceChatRequest;
import camandedit.server.global.config.web.JwtAuthUser;
import camandedit.server.global.config.web.LoginUser;
import camandedit.server.global.response.JsonResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ChatHttpController {

    private final ChatUseCase chatUseCase;

    @GetMapping("/api/chat/workspace")
    public ResponseEntity<?> getChattingByWorkSpace(
        @RequestParam(value = "workSpaceId", required = true) Long workSpaceId,
        @RequestParam(value = "count", required = false, defaultValue = "10") int count,
        @RequestParam(value = "lastId", required = false) Long lastId
    ) {
        List<WorkSpaceChatResponse> result = chatUseCase.findWorkSpaceChat(workSpaceId,
            lastId, count);
        return JsonResponse.okWithData(HttpStatus.OK, "조회성공", result);
    }

    @PostMapping("/api/chat/workspace")
    public ResponseEntity<?> chatWorkSpace(@LoginUser JwtAuthUser user,
        @RequestBody CreateWorkSpaceChatRequest dto) {
        chatUseCase.createChat(dto.toCommand(user.getUserId()));
        return JsonResponse.ok(HttpStatus.OK, "채팅 생성 성공");
    }


}
