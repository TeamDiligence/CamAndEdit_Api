package camandedit.server.unit.user.domain;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import camandedit.server.user.domain.AuthProvider;
import camandedit.server.user.domain.User;
import camandedit.server.user.domain.service.UserPasswordService;
import camandedit.server.user.exception.NotMatchPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserPasswordServiceTest {

  @Mock
  PasswordEncoder passwordEncoder;

  @InjectMocks
  UserPasswordService userPasswordService;


  User user;

  @BeforeEach
  void setUp() {
    user = User.builder()
        .email("email@email.com")
        .name("이름")
        .password("qwer1234")
        .authProvider(AuthProvider.LOCAL)
        .build();
  }

  @Test
  @DisplayName("패스워드 암호화 성공")
  public void passwordEncode() throws Exception {
    String encodePassword = "{bcrypt}encodePassword";
    given(passwordEncoder.encode(user.getPassword())).willReturn(encodePassword);

    User resultUser = userPasswordService.encode(user);
    assertEquals(resultUser.getPassword(), encodePassword);
  }

  @Test
  @DisplayName("패스워드 확인, 일치하지 않으면 Error발생")
  public void passwordCheck() throws Exception {
    String inputPassword = "inputPassword";
    given(passwordEncoder.matches(inputPassword, user.getPassword())).willReturn(false);

    assertThrows(NotMatchPasswordException.class,
        () -> userPasswordService.checkPassword(inputPassword, user));
  }
}
