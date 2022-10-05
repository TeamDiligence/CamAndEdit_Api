package camandedit.server.user.application;

import camandedit.server.user.application.dto.UserResponse;
import camandedit.server.user.domain.User;
import camandedit.server.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserUseCase {

  private final UserRepository userRepository;

  public UserResponse getUserInfo(Long userId){
    User findUser = userRepository.findById(userId);
    return UserResponse.from(findUser);
  }
}
