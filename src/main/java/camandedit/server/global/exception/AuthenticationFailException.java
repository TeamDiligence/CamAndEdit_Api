package camandedit.server.global.exception;

public class AuthenticationFailException extends BusinessException {

    public AuthenticationFailException(String message) {
        super(message, ErrorType.AUTHENTICATION_FAIL);
    }
}
