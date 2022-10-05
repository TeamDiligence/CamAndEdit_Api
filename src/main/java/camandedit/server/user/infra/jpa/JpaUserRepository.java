package camandedit.server.user.infra.jpa;

import camandedit.server.user.domain.AuthProvider;
import camandedit.server.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmailAndAuthProvider(String email, AuthProvider authProvider);
}
