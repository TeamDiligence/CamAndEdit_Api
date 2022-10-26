package camandedit.server.cam.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "cam_meeting_room")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CamMeetingRoom {

  @Id
  private Long roomId;

  @Indexed
  private Long workspaceId;

  private List<ConnectUser> connectUsers = new ArrayList<>();

  public CamMeetingRoom(Long roomId, Long workspaceId) {
    this.roomId = roomId;
    this.workspaceId = workspaceId;
  }

  public void addUser(ConnectUser user) {
    connectUsers.add(user);
  }

  public boolean isParticipation(String sessionId) {
    Optional<ConnectUser> findUser = connectUsers.stream()
        .filter((user) -> user.getSessionId().equals(sessionId))
        .findFirst();
    return findUser.isPresent();
  }

  public void removeUser(String sessionId) {
    connectUsers.removeIf((user) -> user.getSessionId().equals(sessionId));
  }

}
