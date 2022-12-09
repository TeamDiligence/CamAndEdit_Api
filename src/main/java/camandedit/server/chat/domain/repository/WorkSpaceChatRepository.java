package camandedit.server.chat.domain.repository;

import camandedit.server.chat.domain.WorkSpaceChat;
import java.util.List;

public interface WorkSpaceChatRepository {

    void save(WorkSpaceChat workSpaceChat);

    List<WorkSpaceChat> findByWorkSpaceId(Long workSpaceId, Long lastId);
}
