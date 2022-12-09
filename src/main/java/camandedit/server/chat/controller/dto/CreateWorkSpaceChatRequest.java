package camandedit.server.chat.controller.dto;

import camandedit.server.chat.application.command.CreateWorkSpaceChatCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateWorkSpaceChatRequest {

    private Long workSpaceId;
    private String message;

    public CreateWorkSpaceChatCommand toCommand(Long userId) {
        return new CreateWorkSpaceChatCommand(userId, workSpaceId, message);
    }
}
