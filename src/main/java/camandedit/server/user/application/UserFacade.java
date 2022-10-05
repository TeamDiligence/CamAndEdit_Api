package camandedit.server.user.application;

import camandedit.server.user.application.command.CreateUserCommand;
import camandedit.server.user.application.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

  private final CreateUserUseCase createUserUseCase;
  private final GetUserUseCase getUserUseCase;

  public void createUser(CreateUserCommand command){
    createUserUseCase.createUser(command);
  }

  public UserResponse getUserInfo(Long userId){
    return getUserUseCase.getUserInfo(userId);
  }
}
