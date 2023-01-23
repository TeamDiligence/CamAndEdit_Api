package camandedit.server.global.exception;

import lombok.Getter;

@Getter
public enum ErrorType {
  INVALID_INPUT(400),
  EXPIRED_JWT(401),
  AUTHENTICATION_FAIL(401),
  AUTHORIZATION_FAIL(403),
  NOT_FOUND_RESOURCE(404),
  NOT_MATCH_PASSWORD(409),
  ALREADY_PARTICIPATE_WORKSPACE(409),
  ALREADY_PARTICIPATE_CAM_ROOM(409),
  ALREADY_INVITE_EMAIL(409),
  INTERNAL_SERVER_ERROR(500);

  private final int statusCode;

  ErrorType(int statusCode) {
    this.statusCode = statusCode;
  }
}
