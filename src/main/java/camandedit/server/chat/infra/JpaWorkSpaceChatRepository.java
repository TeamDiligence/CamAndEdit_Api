package camandedit.server.chat.infra;

import camandedit.server.chat.domain.WorkSpaceChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaWorkSpaceChatRepository extends JpaRepository<WorkSpaceChat, Long> {

}
