package camandedit.server.document.infra;

import camandedit.server.document.domain.Document;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDocumentRepository extends JpaRepository<Document, Long> {

  List<Document> findAllByWorkSpaceId(Long workspaceId);
}
