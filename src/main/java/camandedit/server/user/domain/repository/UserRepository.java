package camandedit.server.user.domain.repository;

import camandedit.server.user.domain.AuthProvider;
import camandedit.server.user.domain.User;

public interface UserRepository {

  void saveUser(User user);
  User findById(Long id);
  boolean existByEmailByLocal(String email);

  User findByEmailAndAuthProvider(String email, AuthProvider authProvider);
}
