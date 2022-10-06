package camandedit.server.workspace.infra.query;

import static camandedit.server.workspace.domain.QInviteMember.*;

import camandedit.server.workspace.domain.InviteMember;
import camandedit.server.workspace.domain.QInviteMember;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InviteQueryRepository {

  private final JPAQueryFactory queryFactory;

  /*
   *  1시간 이내, 워크 스페이스 마다 승인이 안된 초대 목록 조회
   */
  public List<InviteMember> findAllInviteNotApproveMember(Long workSpaceId) {
    return queryFactory.selectFrom(inviteMember)
        .where(isWorkSpace(workSpaceId), isOneHourAfter(), isInviteFalse())
        .fetch();
  }

  /*
   *  1시간 이내, 해당 이메일과 워크스페이스로 승인이 안된 유저 조회
   */

  public InviteMember findByEamilAndWorkSpaceId(String email, Long workSpaceId) {
    return queryFactory.selectFrom(inviteMember)
        .where(isInviteFalse(), isOneHourAfter(), isWorkSpace(workSpaceId),
            inviteMember.email.eq(email))
        .fetchOne();
  }

  private BooleanExpression isWorkSpace(Long workSpaceId) {
    return inviteMember.workSpaceId.eq(workSpaceId);
  }

  private BooleanExpression isOneHourAfter() {
    return inviteMember.inviteRequestTime.after(LocalDateTime.now().minusHours(1));
  }

  private BooleanExpression isInviteFalse() {
    return inviteMember.isInvited.isFalse();
  }
}
