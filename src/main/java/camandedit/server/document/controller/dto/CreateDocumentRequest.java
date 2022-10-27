package camandedit.server.document.controller.dto;

import camandedit.server.document.application.command.CreateDocumentCommand;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public record CreateDocumentRequest(@NotBlank @Size(min = 1, max = 20) String title,
                                    @NotBlank @Size(min = 1, max = 50) String description,
                                    @NotNull Long workspaceId) {

  public CreateDocumentCommand toCommand(Long userId) {
    return CreateDocumentCommand.builder()
        .userId(userId)
        .workspaceId(workspaceId)
        .description(description)
        .title(title)
        .build();
  }
}
