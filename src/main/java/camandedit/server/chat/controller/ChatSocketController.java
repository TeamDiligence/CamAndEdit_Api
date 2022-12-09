package camandedit.server.chat.controller;

import camandedit.server.chat.application.ChatUseCase;
import camandedit.server.chat.application.command.CreateWorkSpaceChatCommand;
import camandedit.server.chat.controller.dto.WorkSpaceChatSocketResponse;
import camandedit.server.chat.domain.WorkSpaceChat;
import camandedit.server.global.config.socket.SocketUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatSocketController {

    private final ChatUseCase chatUseCase;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/workspace/chat/{workSpaceId}")
    public void workspaceChat(@Payload String message, @DestinationVariable("workSpaceId") Long id,
        @Header("simpUser") SocketUser user) {
        WorkSpaceChat chat = chatUseCase.createChat(
            new CreateWorkSpaceChatCommand(user.getUserId(), id, message));
        messagingTemplate.convertAndSend("/topic/workspace/chat" + id,
            WorkSpaceChatSocketResponse.of(chat));
    }
}
