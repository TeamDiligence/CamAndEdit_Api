package camandedit.server.chat.infra;

import camandedit.server.chat.domain.WorkSpaceChat;
import camandedit.server.chat.domain.repository.WorkSpaceChatRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class WorkSpaceChatRepositoryImpl implements WorkSpaceChatRepository {

    private final JpaWorkSpaceChatRepository jpaWorkSpaceChatRepository;
    private final WorkSpaceChatQueryRepository workSpaceChatQueryRepository;

    @Override
    public void save(WorkSpaceChat workSpaceChat) {
        jpaWorkSpaceChatRepository.save(workSpaceChat);
    }

    @Override
    public List<WorkSpaceChat> findByWorkSpaceId(Long workSpaceId, Long lastId, int count) {
        return workSpaceChatQueryRepository.findWorkSpaceChatByLastId(workSpaceId,lastId,count);
    }
}
