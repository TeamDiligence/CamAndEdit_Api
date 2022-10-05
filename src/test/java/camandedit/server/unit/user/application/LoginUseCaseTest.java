package camandedit.server.unit.user.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import camandedit.server.global.exception.NotFoundResourceException;
import camandedit.server.user.application.LoginUseCase;
import camandedit.server.user.application.command.LoginCommand;
import camandedit.server.user.application.dto.TokenResponse;
import camandedit.server.user.domain.AuthProvider;
import camandedit.server.user.domain.User;
import camandedit.server.user.domain.repository.UserRepository;
import camandedit.server.user.domain.service.TokenProvider;
import camandedit.server.user.domain.service.UserPasswordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LoginUseCaseTest {

  @Mock
  UserRepository userRepository;

  @Mock
  UserPasswordService userPasswordService;

  @Mock
  TokenProvider tokenProvider;

  @InjectMocks
  LoginUseCase loginUseCase;

  LoginCommand loginCommand;

  @BeforeEach
  void setUp() {
    loginCommand = new LoginCommand("email@email.com", "password");
  }

  @Test
  @DisplayName("없는 유저 로그인 시도 실패")
  public void not_existUser() throws Exception {
    given(userRepository.findByEmailAndAuthProvider(loginCommand.getEmail(),
        AuthProvider.LOCAL)).willReturn(null);

    assertThrows(NotFoundResourceException.class, () ->
        loginUseCase.login(loginCommand));
  }

  @Test
  @DisplayName("로그인 성공")
  public void not_match_password() throws Exception {
    User user = User.builder().build();
    String accessToken = "accessToken";
    given(userRepository.findByEmailAndAuthProvider(loginCommand.getEmail(),
        AuthProvider.LOCAL)).willReturn(user);
    given(tokenProvider.createToken(user)).willReturn(accessToken);

    TokenResponse result = loginUseCase.login(loginCommand);

    assertEquals(result.getAccessToken(), accessToken);
  }
}
