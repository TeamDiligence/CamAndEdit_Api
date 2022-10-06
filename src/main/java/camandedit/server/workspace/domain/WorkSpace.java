package camandedit.server.workspace.domain;

import camandedit.server.global.common.BaseTimeJpaEntity;
import camandedit.server.global.exception.AuthorizationFailException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WorkSpace extends BaseTimeJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "workspace_id")
  private Long id;

  @Column(nullable = false)
  private String name;

  private String logoImage;

  private Long adminId;

  @OneToMany(mappedBy = "workSpace", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<WorkSpaceMember> workSpaceMembers = new ArrayList<>();

  @OneToMany(mappedBy = "workSpace", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MeetingRoom> meetingRoomList = new ArrayList<>();

  @Builder
  public WorkSpace(String name, String logoImage, Long adminId) {
    this.name = name;
    this.logoImage = logoImage;
    this.adminId = adminId;
  }

  public static WorkSpace initWorkSpace(String name, String logoImage, Long userId) {
    return WorkSpace.builder().name(name).logoImage(logoImage).adminId(userId)
        .build();
  }

  public void addMember(WorkSpaceMember workSpaceMember) {
    workSpaceMembers.add(workSpaceMember);
  }

  public void addMeetingRoom(MeetingRoom meetingRoom){
    meetingRoomList.add(meetingRoom);
  }

  public void checkAdmin(Long userId){
    if(userId != this.adminId){
      throw new AuthorizationFailException("워크스페이스 소유자가 아닙니다.");
    }
  }

}
