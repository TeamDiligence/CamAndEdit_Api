package camandedit.server.user.application;

import camandedit.server.global.exception.InvalidInputException;
import camandedit.server.global.exception.NotFoundResourceException;
import camandedit.server.user.application.command.LoginCommand;
import camandedit.server.user.application.dto.TokenResponse;
import camandedit.server.user.domain.AuthProvider;
import camandedit.server.user.domain.User;
import camandedit.server.user.domain.repository.UserRepository;
import camandedit.server.user.domain.service.TokenProvider;
import camandedit.server.user.domain.service.UserPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUseCase {

  private final UserRepository userRepository;
  private final UserPasswordService userPasswordService;
  private final TokenProvider tokenProvider;

  public TokenResponse login(LoginCommand command){
    User user = getUser(command.getEmail());
    userPasswordService.checkPassword(command.getPassword(), user);

    String accessToken = tokenProvider.createToken(user);
    return new TokenResponse(accessToken);
  }

  private User getUser(String email) {
    User user = userRepository.findByEmailAndAuthProvider(email,
        AuthProvider.LOCAL);
    if(user == null){
      throw new NotFoundResourceException("해당 유저를 찾을 수 없습니다.");
    }
    return user;
  }

}
