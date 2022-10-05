package camandedit.server.unit.user.application.command;

import static org.junit.jupiter.api.Assertions.*;

import camandedit.server.user.application.command.CreateUserCommand;
import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreateUserCommandTest {

  @Test
  @DisplayName("유효하지 않는 요청 값 실패")
  public void not_matchPassword_Format() throws Exception {
    String notFormatPassword = "qwer1234!";
    String email = "email@email.com";
    String name = "names";
    assertThrows(ConstraintViolationException.class, () ->
        CreateUserCommand.builder()
            .email(email)
            .name(name)
            .password(notFormatPassword)
            .build()
    );
  }
}
