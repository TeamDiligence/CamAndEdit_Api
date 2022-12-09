package camandedit.server.chat.infra;

import static camandedit.server.chat.domain.QWorkSpaceChat.workSpaceChat;
import static camandedit.server.workspace.domain.QWorkSpaceMember.workSpaceMember;

import camandedit.server.chat.domain.WorkSpaceChat;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WorkSpaceChatQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<WorkSpaceChat> findWorkSpaceChatByLastId(Long workSpaceId, Long lastId, int count) {
        return queryFactory.selectFrom(workSpaceChat)
            .join(workSpaceChat.member, workSpaceMember).fetchJoin()
            .where(workSpaceChat.member.workSpace.id.eq(workSpaceId),
                lastIdCondition(lastId))
            .orderBy(workSpaceChat.id.desc())
            .limit(count)
            .fetch();
    }

    public BooleanExpression lastIdCondition(Long lastId) {
        return lastId == null ? null : workSpaceChat.id.lt(lastId);
    }
}
