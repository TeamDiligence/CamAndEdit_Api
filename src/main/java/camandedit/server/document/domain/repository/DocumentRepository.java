package camandedit.server.document.domain.repository;

import camandedit.server.document.domain.Document;
import java.util.List;

public interface DocumentRepository {

  void save(Document document);

  Document findById(Long id);
  List<Document> findAllByWorkspaceId(Long workspaceId);
}
