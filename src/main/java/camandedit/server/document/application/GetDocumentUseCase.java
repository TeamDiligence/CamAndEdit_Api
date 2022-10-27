package camandedit.server.document.application;

import camandedit.server.document.application.dto.DocumentResponse;
import camandedit.server.document.domain.Document;
import camandedit.server.document.domain.repository.DocumentRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetDocumentUseCase {

  private final DocumentRepository documentRepository;


  @Transactional(readOnly = true)
  public DocumentResponse findOne(Long id) {
    Document document = documentRepository.findById(id);
    return DocumentResponse.from(document);
  }

  @Transactional(readOnly = true)
  public List<DocumentResponse> findListByWorkspace(Long workspaceId) {
    List<Document> documentList = documentRepository.findAllByWorkspaceId(workspaceId);
    return documentList.stream().map(DocumentResponse::from).collect(Collectors.toList());
  }


}
