package camandedit.server.global.security.dto;

import camandedit.server.user.domain.AuthProvider;
import camandedit.server.user.domain.User;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OAuthAttributes {

  private Map<String, Object> attributes;
  private String name;
  private String email;
  private String picture;
  private String nameAttributeKey;
  private AuthProvider authProvider;

  public static OAuthAttributes of(String registtrationId, String userNameAttributeName,
      Map<String, Object> attributes) {
    return switch (registtrationId) {
      case "google" -> ofGoogle(userNameAttributeName, attributes);
      default -> throw new RuntimeException("not support");
    };
  }

  private static OAuthAttributes ofGoogle(String userNameAttributeName,
      Map<String, Object> attributes) {
    return OAuthAttributes.builder()
        .name((String)attributes.get("name"))
        .email((String) attributes.get("email"))
        .picture((String) attributes.get("picture"))
        .attributes(attributes)
        .nameAttributeKey(userNameAttributeName)
        .authProvider(AuthProvider.GOOGLE)
        .build();
  }

  public User toEntity(){
    return User.builder()
        .name(name)
        .email(email)
        .userImage(picture)
        .authProvider(AuthProvider.GOOGLE)
        .build();
  }
}
