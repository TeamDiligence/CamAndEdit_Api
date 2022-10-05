package camandedit.server.user.domain.service;


import camandedit.server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPasswordService {

  private final PasswordEncoder passwordEncoder;

  public User encode(User user){
    String password = user.getPassword();
    String encode = passwordEncoder.encode(password);
    user.encodePassword(encode);
    return user;
  }

  public void checkPassword(String inputPassword, User user){

  }
}
