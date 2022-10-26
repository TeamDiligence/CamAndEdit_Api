package camandedit.server.cam.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@NoArgsConstructor
@ToString
@RedisHash("connect_user")
public class ConnectUser {


  @Id
  private String sessionId;
  @Indexed
  private Long userId;
  private String nickname;

  private String userImage;

  private Long workSpaceId;

  private Long meetingRoomId;

  @Builder
  public ConnectUser(String sessionId, Long userId, String nickname, String userImage,
      Long workSpaceId) {
    this.sessionId = sessionId;
    this.userId = userId;
    this.nickname = nickname;
    this.userImage = userImage;
    this.workSpaceId = workSpaceId;
  }

  public boolean isSubscribeMeetingRoom(){
    return meetingRoomId != null;
  }

  public void subMeetingRoom(Long meetingRoomId){
    this.meetingRoomId = meetingRoomId;
  }

}
