package camandedit.server.chat.application.command;

import camandedit.server.global.common.SelfValidator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateWorkSpaceChatCommand extends SelfValidator<CreateWorkSpaceChatCommand> {

    @NotNull
    private Long userId;
    @NotNull
    private Long workSpaceId;

    @NotBlank
    private String message;

    public CreateWorkSpaceChatCommand(Long userId, Long workSpaceId, String message) {
        this.userId = userId;
        this.workSpaceId = workSpaceId;
        this.message = message;
        this.validationSelf();
    }
}
