package camandedit.server.workspace.domain;


import camandedit.server.global.common.BaseTimeJpaEntity;
import camandedit.server.user.domain.User;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class WorkSpaceMember extends BaseTimeJpaEntity {

  @EmbeddedId
  private WorkSpaceMemberKey workSpaceMemberKey;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("user_id")
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("workspace_id")
  @JoinColumn(name = "workspace_id")
  private WorkSpace workSpace;

  @Column(nullable = false)
  private String nickname;

  private String description;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, name = "member_role")
  private MemberRole memberRole;

  @Builder
  public WorkSpaceMember(User user, WorkSpace workSpace, String nickname, String description,
      MemberRole memberRole) {
    this.workSpaceMemberKey = new WorkSpaceMemberKey(user.getId(), workSpace.getId());
    this.user = user;
    this.workSpace = workSpace;
    this.nickname = nickname;
    this.description = description;
    this.memberRole = memberRole;
  }

  public static WorkSpaceMember createAdmin(User user, WorkSpace workSpace){
    return WorkSpaceMember.builder()
        .user(user)
        .workSpace(workSpace)
        .nickname(user.getName())
        .memberRole(MemberRole.ADMIN)
        .build();
  }

  public static WorkSpaceMember createMember(User user, WorkSpace workSpace){
    return WorkSpaceMember.builder()
        .user(user)
        .workSpace(workSpace)
        .nickname(user.getName())
        .memberRole(MemberRole.MEMBER)
        .build();
  }
}
