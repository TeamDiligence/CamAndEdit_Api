package camandedit.server.workspace.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class WorkSpaceMemberKey implements Serializable {

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "workspace_id")
  private Long workSpaceId;

  public WorkSpaceMemberKey(Long userId, Long workSpaceId) {
    this.userId = userId;
    this.workSpaceId = workSpaceId;
  }

  public WorkSpaceMemberKey() {

  }
}
