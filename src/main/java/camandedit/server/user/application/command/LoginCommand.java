package camandedit.server.user.application.command;

import camandedit.server.global.common.SelfValidator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class LoginCommand extends SelfValidator<LoginCommand> {

  @Email
  private String email;
  @NotBlank
  @Pattern(regexp = "[a-zA-Z0-9]{8,16}")
  private String password;

  public LoginCommand(String email, String password) {
    this.email = email;
    this.password = password;
    this.validationSelf();
  }
}
