package camandedit.server.global.exception;

import camandedit.server.global.response.JsonResponse;
import java.io.IOException;
import javax.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  /*
   *  business Exception
   */
  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<?> handleBusinessException(BusinessException e) {
    return JsonResponse.fail(e.getErrorType(), e.getMessage());
  }

  /*
   *  잘못된 형식의 요청
   */
  @ExceptionHandler(value = {HttpMessageNotReadableException.class,
      HttpRequestMethodNotSupportedException.class})
  public ResponseEntity<?> handleDateTimeFormatException(HttpMessageNotReadableException e)
      throws IOException {

    return JsonResponse.fail(ErrorType.INVALID_INPUT, "잘못된 요청입니다.");
  }


  /*
   *  validation Exception
   *  Client의 요청 검증 실패.
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e
  ) throws IOException {

    return JsonResponse.fail(ErrorType.INVALID_INPUT, "잘못된 값입니다.");
  }

  /*
   *  Server Error.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> internalServerException(Exception e) throws IOException {
    e.printStackTrace();
    return JsonResponse.fail(ErrorType.INTERNAL_SERVER_ERROR, "서버 에러");
  }
}
