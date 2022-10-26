package camandedit.server.cam.application;

import camandedit.server.cam.domain.ConnectUser;
import camandedit.server.cam.infra.ConnectUserRepository;
import camandedit.server.global.exception.NotFoundResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetWorkSpaceMeetingRoomUseCase {

  private final ConnectUserRepository connectUserRepository;

  public ConnectUser getCurrentUser(String sessionId){
    return connectUserRepository.findById(sessionId)
        .orElseThrow(()-> new NotFoundResourceException("세션 정보를 찾을 수 없습니다."));
  }
}
