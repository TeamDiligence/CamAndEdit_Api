package camandedit.server.user.controller.dto;

import camandedit.server.user.application.command.LoginCommand;

public record LoginRequest(String email, String password) {

  public LoginCommand toCommand(){
    return new LoginCommand(this.email, this.password);
  }
}
