package camandedit.server.cam.exception;

import camandedit.server.global.exception.BusinessException;
import camandedit.server.global.exception.ErrorType;

public class AlreadyPariticipateCamRoomException extends BusinessException {

  public AlreadyPariticipateCamRoomException(String message) {
    super(message, ErrorType.ALREADY_PARTICIPATE_CAM_ROOM);
  }
}
