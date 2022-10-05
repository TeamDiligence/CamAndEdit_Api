package camandedit.server.user.domain.service;


import camandedit.server.user.domain.User;
import camandedit.server.user.exception.NotMatchPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPasswordService {

  private final PasswordEncoder passwordEncoder;

  public User encode(User user) {
    String password = user.getPassword();
    String encode = passwordEncoder.encode(password);
    user.encodePassword(encode);
    return user;
  }

  public void checkPassword(String inputPassword, User user) {
    if (!passwordEncoder.matches(inputPassword, user.getPassword())) {
      throw new NotMatchPasswordException("패스워드가 일치하지 않습니다.");
    }
  }
}
