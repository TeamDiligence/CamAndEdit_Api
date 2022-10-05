package camandedit.server.user.application;

import camandedit.server.user.application.command.CreateUserCommand;
import camandedit.server.user.application.command.LoginCommand;
import camandedit.server.user.application.dto.TokenResponse;
import camandedit.server.user.application.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

  private final CreateUserUseCase createUserUseCase;
  private final GetUserUseCase getUserUseCase;
  private final LoginUseCase loginUseCase;

  public void createUser(CreateUserCommand command){
    createUserUseCase.createUser(command);
  }

  public UserResponse getUserInfo(Long userId){
    return getUserUseCase.getUserInfo(userId);
  }

  public TokenResponse login(LoginCommand command){
    return loginUseCase.login(command);
  }
}
