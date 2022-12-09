package camandedit.server.document.application;

import camandedit.server.document.application.command.CreateDocumentCommand;
import camandedit.server.document.domain.Document;
import camandedit.server.document.domain.repository.DocumentRepository;
import camandedit.server.workspace.domain.WorkSpace;
import camandedit.server.workspace.domain.repository.WorkSpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateDocumentUseCase {

  private final DocumentRepository documentRepository;
  private final WorkSpaceRepository workSpaceRepository;

  @Transactional
  public void create(CreateDocumentCommand command){
    WorkSpace workspace = workSpaceRepository.findById(command.getWorkSpaceId());
    Document document = command.toEntity(workspace);
    documentRepository.save(document);
  }
}
