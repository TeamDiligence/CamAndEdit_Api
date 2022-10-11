package camandedit.server.global.security.service;

import camandedit.server.global.security.dto.OAuthAttributes;
import camandedit.server.global.security.dto.OAuthUserPrincipal;
import camandedit.server.user.domain.AuthProvider;
import camandedit.server.user.domain.User;
import camandedit.server.user.domain.repository.UserRepository;
import camandedit.server.user.infra.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

  private final UserRepository userRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
    OAuth2User oAuth2User = delegate.loadUser(userRequest);

    String registrationId = userRequest.getClientRegistration().getRegistrationId();
    String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
        .getUserInfoEndpoint().getUserNameAttributeName();
    OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName,
        oAuth2User.getAttributes());
    User user = saveAndUpdate(attributes);
    return OAuthUserPrincipal.of(user, attributes.getAttributes());
  }

  private User saveAndUpdate(OAuthAttributes attributes) {
    String eamil = attributes.getEmail();
    AuthProvider authProvider = attributes.getAuthProvider();
    User findUser = userRepository.findByEmailAndAuthProviderNullable(eamil, authProvider);
    if (findUser == null) {
      findUser = userRepository.saveUser(attributes.toEntity());
    } else {
      findUser.updateProfile(attributes.getName(), attributes.getPicture());
    }

    return findUser;
  }


}
