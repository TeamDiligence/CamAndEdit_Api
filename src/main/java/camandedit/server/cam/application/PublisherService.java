package camandedit.server.cam.application;


import camandedit.server.global.exception.ErrorType;

public interface PublisherService {

  void sendMeetingRoomCurrentUser(Long workSpaceId);

  void sendErrorToUser(String sessionId, ErrorType errorType, String message);
}
