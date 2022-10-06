package camandedit.server.workspace.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "meeting_room")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MeetingRoom {

  @Id
  @GeneratedValue
  @Column(name = "meeting_room_id")
  private Long id;

  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  private WorkSpace workSpace;

  public MeetingRoom(String name, WorkSpace workSpace) {
    this.name = name;
    this.workSpace = workSpace;
  }
}
