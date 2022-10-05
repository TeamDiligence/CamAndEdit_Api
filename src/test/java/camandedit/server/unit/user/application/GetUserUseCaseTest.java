package camandedit.server.unit.user.application;

import static org.mockito.BDDMockito.given;

import camandedit.server.user.application.GetUserUseCase;
import camandedit.server.user.application.dto.UserResponse;
import camandedit.server.user.domain.AuthProvider;
import camandedit.server.user.domain.User;
import camandedit.server.user.domain.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetUserUseCaseTest {

  @Mock
  UserRepository userRepository;

  @InjectMocks
  GetUserUseCase getUserUseCase;

  @Test
  @DisplayName("유저 조회 성공")
  public void getUserInfo() throws Exception {
    User user = User.builder()
        .email("email@email.com")
        .name("name")
        .password("password")
        .userImage(null)
        .authProvider(AuthProvider.LOCAL)
        .build();
    given(userRepository.findById(1L)).willReturn(user);

    UserResponse result = getUserUseCase.getUserInfo(1L);

    Assertions.assertEquals(user.getEmail(), result.getEmail());
  }
}
