package camandedit.server.chat.domain;

import camandedit.server.global.common.BaseTimeJpaEntity;
import camandedit.server.user.domain.User;
import camandedit.server.workspace.domain.WorkSpace;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkSpaceChat extends BaseTimeJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workspace_chat_id")
    private Long id;

    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    private WorkSpace workSpace;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public WorkSpaceChat(String message, WorkSpace workSpace, User user) {
        this.message = message;
        this.workSpace = workSpace;
        this.user = user;
    }
}
