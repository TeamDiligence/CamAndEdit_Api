package camandedit.server.user.application;

import camandedit.server.user.application.dto.UserResponse;
import camandedit.server.user.domain.User;
import camandedit.server.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetUserUseCase {

  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public UserResponse getUserInfo(Long userId){
    User findUser = userRepository.findById(userId);
    return UserResponse.from(findUser);
  }
}
