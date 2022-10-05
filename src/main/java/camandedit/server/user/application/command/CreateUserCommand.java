package camandedit.server.user.application.command;

import camandedit.server.global.common.SelfValidator;
import camandedit.server.user.domain.AuthProvider;
import camandedit.server.user.domain.User;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateUserCommand extends SelfValidator<CreateUserCommand> {

  @Email
  private String email;

  @NotBlank
  private String name;

  @NotBlank
  @Pattern(regexp = "[a-zA-Z0-9]{8,16}")
  private String password;

  @Builder
  public CreateUserCommand(String email, String name, String password) {
    this.email = email;
    this.name = name;
    this.password = password;
    this.validationSelf();
  }

  public User toEntity(){
    return User.builder()
        .email(email)
        .name(name)
        .authProvider(AuthProvider.LOCAL)
        .password(password)
        .build();
  }
}
