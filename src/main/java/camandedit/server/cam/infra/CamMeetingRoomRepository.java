package camandedit.server.cam.infra;

import camandedit.server.cam.domain.CamMeetingRoom;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CamMeetingRoomRepository extends CrudRepository<CamMeetingRoom, Long> {

  List<CamMeetingRoom> findAllByWorkspaceId(Long workSpaceId);
}
