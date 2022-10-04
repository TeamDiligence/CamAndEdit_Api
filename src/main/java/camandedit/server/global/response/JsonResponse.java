package camandedit.server.global.response;

import camandedit.server.global.exception.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class JsonResponse {

  public static ResponseEntity<?> ok(HttpStatus status, String message) {
    SuccessResponse response = new SuccessResponse(true, message, null);
    return ResponseEntity.status(status.value())
        .body(response);
  }


  public static ResponseEntity<?> okWithData(HttpStatus status, String message, Object data) {
    SuccessResponse response = new SuccessResponse(true, message, data);
    return ResponseEntity.status(status.value())
        .body(response);
  }

  public static ResponseEntity<?> fail(ErrorType errorType, String message) {
    FailResponse response = new FailResponse(false, message, errorType.getStatusCode());
    return ResponseEntity.status(errorType.getStatusCode())
        .body(response);
  }
}
