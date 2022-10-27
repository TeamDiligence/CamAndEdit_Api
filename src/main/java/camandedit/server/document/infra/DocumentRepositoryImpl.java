package camandedit.server.document.infra;

import camandedit.server.document.domain.Document;
import camandedit.server.document.domain.repository.DocumentRepository;
import camandedit.server.global.exception.NotFoundResourceException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentRepositoryImpl implements DocumentRepository {

  private final JpaDocumentRepository jpaDocumentRepository;

  @Override
  public void save(Document document) {
    jpaDocumentRepository.save(document);
  }

  @Override
  public Document findById(Long id) {
    return jpaDocumentRepository.findById(id)
        .orElseThrow(()-> new NotFoundResourceException("해당 문서를 찾을 수 없습니다."));
  }

  @Override
  public List<Document> findAllByWorkspaceId(Long workspaceId) {
    return jpaDocumentRepository.findAllByWorkSpaceId(workspaceId);
  }
}
