package camandedit.server.cam.infra;

import camandedit.server.workspace.domain.MeetingRoom;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMeetingRoomRepository extends JpaRepository<MeetingRoom, Long> {

}
