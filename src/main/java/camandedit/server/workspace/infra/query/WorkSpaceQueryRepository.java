package camandedit.server.workspace.infra.query;

import static camandedit.server.user.domain.QUser.*;
import static camandedit.server.workspace.domain.QMeetingRoom.*;
import static camandedit.server.workspace.domain.QWorkSpace.*;
import static camandedit.server.workspace.domain.QWorkSpaceMember.workSpaceMember;


import camandedit.server.user.domain.QUser;
import camandedit.server.workspace.domain.QMeetingRoom;
import camandedit.server.workspace.domain.WorkSpace;
import camandedit.server.workspace.domain.WorkSpaceMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WorkSpaceQueryRepository {

  private final JPAQueryFactory queryFactory;

  public List<WorkSpace> findWorkSpaceListByUserId(Long userId) {
    return queryFactory.selectFrom(workSpace)
        .join(workSpace.workSpaceMembers, workSpaceMember).fetchJoin()
        .where(workSpaceMember.user.id.eq(userId))
        .fetch();
  }

  public WorkSpace findByIdWithMeetingRoom(Long workSpaceId) {
    return queryFactory.selectFrom(workSpace)
        .leftJoin(workSpace.meetingRoomList, meetingRoom).fetchJoin()
        .where(workSpace.id.eq(workSpaceId))
        .fetchOne();
  }

  public WorkSpace findByIdWithMember(Long workSpaceId) {
    return queryFactory.selectFrom(workSpace)
        .leftJoin(workSpace.workSpaceMembers, workSpaceMember).fetchJoin()
        .leftJoin(workSpaceMember.user, user).fetchJoin()
        .where(workSpace.id.eq(workSpaceId))
        .fetchOne();
  }

  public List<WorkSpaceMember> findMemberByWorkSpaceId(Long userId, Long workSpaceId) {
    return queryFactory.selectFrom(workSpaceMember)
        .where(workSpaceMember.workSpace.id.eq(workSpaceId).and(workSpaceMember.user.id.eq(userId)))
        .fetch();
  }

  public WorkSpaceMember findMemberWithUser(Long userId, Long workSpaceId) {
    return queryFactory.selectFrom(workSpaceMember)
        .leftJoin(workSpaceMember.user, user).fetchJoin()
        .where(workSpaceMember.user.id.eq(userId),
            workSpaceMember.workSpace.id.eq(workSpaceId))
        .fetchOne();
  }
}
