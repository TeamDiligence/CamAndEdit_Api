package camandedit.server.document.application.command;

import camandedit.server.document.domain.Document;
import camandedit.server.workspace.domain.WorkSpace;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateDocumentCommand {

  private Long workSpaceId;
  private Long userId;
  private String title;
  private String description;


  public Document toEntity(WorkSpace workSpace){
    return Document.builder()
        .ownerId(userId)
        .title(title)
        .description(description)
        .workSpace(workSpace)
        .build();
  }
}
