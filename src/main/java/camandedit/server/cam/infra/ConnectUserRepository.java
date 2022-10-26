package camandedit.server.cam.infra;

import camandedit.server.cam.domain.ConnectUser;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ConnectUserRepository extends CrudRepository<ConnectUser, String> {

  List<ConnectUser> findAllByUserId(Long userId);
}
