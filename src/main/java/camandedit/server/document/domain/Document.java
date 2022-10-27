package camandedit.server.document.domain;

import camandedit.server.global.common.BaseTimeJpaEntity;
import camandedit.server.workspace.domain.WorkSpace;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Document extends BaseTimeJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "document_id")
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private Long ownerId;
  @Column(nullable = false)
  private String description;

  @Column(columnDefinition = "TEXT")
  private String contents;

  @ManyToOne(fetch = FetchType.LAZY)
  private WorkSpace workSpace;


  @Builder
  public Document(String title, Long ownerId, String description, String contents,
      WorkSpace workSpace) {
    this.title = title;
    this.ownerId = ownerId;
    this.description = description;
    this.contents = contents;
    this.workSpace = workSpace;
  }

  public void modifyContents(String contents) {
    this.contents = contents;
  }

}
