package camandedit.server.user.infra;

import camandedit.server.global.exception.NotFoundResourceException;
import camandedit.server.user.domain.AuthProvider;
import camandedit.server.user.domain.User;
import camandedit.server.user.domain.repository.UserRepository;
import camandedit.server.user.infra.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final JpaUserRepository jpaUserRepository;

  @Override
  public User saveUser(User user) {
    return jpaUserRepository.save(user);
  }

  @Override
  public User findById(Long id) {
    return jpaUserRepository.findById(id)
        .orElseThrow(() -> new NotFoundResourceException("해당 유저를 찾을 수 없습니다."));
  }

  @Override
  public boolean existByEmailByLocal(String email) {
    return jpaUserRepository.existsByEmailAndAuthProvider(email, AuthProvider.LOCAL);
  }

  @Override
  public User findByEmailAndAuthProviderNullable(String email, AuthProvider authProvider) {
    User user = jpaUserRepository.findByEmailAndAuthProvider(email, authProvider)
        .orElseThrow(() -> new NotFoundResourceException("해당 유저를 찾을 수 없습니다."));
    return user;
  }
}
