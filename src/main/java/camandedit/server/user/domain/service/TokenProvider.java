package camandedit.server.user.domain.service;

import camandedit.server.user.domain.User;
import org.springframework.security.core.Authentication;

public interface TokenProvider {

  String createToken(User user);

  boolean validationToken(String token);

  Authentication getAuthentication(String token);
}
