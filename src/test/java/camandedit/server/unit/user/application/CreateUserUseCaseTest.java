package camandedit.server.unit.user.application;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import camandedit.server.global.exception.InvalidInputException;
import camandedit.server.user.application.CreateUserUseCase;
import camandedit.server.user.application.command.CreateUserCommand;
import camandedit.server.user.domain.User;
import camandedit.server.user.domain.repository.UserRepository;
import camandedit.server.user.domain.service.UserPasswordService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseTest {

  @Mock
  UserRepository userRepository;

  @Mock
  UserPasswordService userPasswordService;

  @InjectMocks
  CreateUserUseCase createUserUseCase;

  CreateUserCommand command;

  @BeforeEach
  void setUp() {
    command = CreateUserCommand.builder()
        .email("email@email.com")
        .name("name")
        .password("password1")
        .build();
  }

  @Test
  @DisplayName("이미 사용중인 email로 실패")
  public void already_use_email() throws Exception {
    given(userRepository.existByEmailByLocal("email@email.com")).willReturn(true);
    assertThrows(InvalidInputException.class, () -> createUserUseCase.createUser(command));
  }

  @Test
  @DisplayName("회원가입 성공")
  public void create_user_success() throws Exception {

    User encoderUser = User.builder().build();
    given(userRepository.existByEmailByLocal("email@email.com")).willReturn(false);
    given(userPasswordService.encode(any())).willReturn(encoderUser);

    createUserUseCase.createUser(command);

    verify(userRepository, times(1)).saveUser(encoderUser);
  }
}
