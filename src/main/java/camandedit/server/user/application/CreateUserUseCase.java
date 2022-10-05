package camandedit.server.user.application;


import camandedit.server.global.exception.InvalidInputException;
import camandedit.server.user.application.command.CreateUserCommand;
import camandedit.server.user.domain.User;
import camandedit.server.user.domain.repository.UserRepository;
import camandedit.server.user.domain.service.UserPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateUserUseCase {

  private final UserRepository userRepository;
  private final UserPasswordService userPasswordService;

  public void createUser(CreateUserCommand command) {
    checkExistEmail(command.getEmail());
    User user = userPasswordService.encode(command.toEntity());
    userRepository.saveUser(user);
  }

  private void checkExistEmail(String email) {
    if (userRepository.existByEmailByLocal(email)) {
      throw new InvalidInputException("이미 사용중인 email입니다.");
    }
  }

}
