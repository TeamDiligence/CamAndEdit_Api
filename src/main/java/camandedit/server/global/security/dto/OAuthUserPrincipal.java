package camandedit.server.global.security.dto;

import camandedit.server.user.domain.User;
import java.util.Collection;
import java.util.Map;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
public class OAuthUserPrincipal implements OAuth2User {

  private User user;
  private Map<String,Object> attributes;

  private OAuthUserPrincipal(User user, Map<String, Object> attributes) {
    this.user = user;
    this.attributes = attributes;
  }

  public static OAuthUserPrincipal of(User user, Map<String,Object> attributes){
    return new OAuthUserPrincipal(user, attributes);
  }

  @Override
  public Map<String, Object> getAttributes() {
    return this.attributes;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getName() {
    return user.getName();
  }

  public String getEmail(){
    return user.getEmail();
  }

  public Long getUserId(){
    return user.getId();
  }
}
