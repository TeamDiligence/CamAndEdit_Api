package camandedit.server.chat.controller;

import camandedit.server.global.config.socket.SocketUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatSocketController {

    @MessageMapping("/workspace/chat/{workSpaceId}")
    public void workspaceChat(@Payload String message, @DestinationVariable("workSpaceId") Long id,
        @Header("simpUser") SocketUser user) {

    }
}
