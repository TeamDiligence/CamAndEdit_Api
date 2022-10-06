package camandedit.server.workspace.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "invite_member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InviteMember {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "invite_id")
  private Long id;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private Long workSpaceId;

  private Boolean isInvited;

  private LocalDateTime inviteRequestTime;

  @Builder
  public InviteMember(String email, Long workSpaceId, Boolean isInvited,
      LocalDateTime inviteRequestTime) {
    this.email = email;
    this.workSpaceId = workSpaceId;
    this.isInvited = isInvited;
    this.inviteRequestTime = inviteRequestTime;
  }

  public static InviteMember inviteInit(String email, Long workSpaceId) {
    return InviteMember.builder()
        .email(email)
        .workSpaceId(workSpaceId)
        .isInvited(false)
        .inviteRequestTime(LocalDateTime.now())
        .build();
  }

  public void invite() {
    this.isInvited = true;
  }

}
