package camandedit.server.user.controller.dto;

import camandedit.server.user.application.command.CreateUserCommand;

public record CreateUserRequest(String email, String name, String password) {

  public CreateUserCommand toCommand(){
    return CreateUserCommand.builder()
        .email(email)
        .name(name)
        .password(password)
        .build();
  }

}
