package camandedit.server.chat.infra;

import camandedit.server.chat.domain.WorkSpaceChat;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WorkSpaceChatQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<WorkSpaceChat> findWorkSpaceChatByLastId(Long workSpaceId, Long lastId) {
        return null;
    }
}
