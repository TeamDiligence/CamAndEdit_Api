package camandedit.server.global.security;

import camandedit.server.global.security.handler.OAuthSuccessHandler;
import camandedit.server.global.security.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  private final CustomOAuth2UserService customOAuth2UserService;
  private final OAuthSuccessHandler oAuthSuccessHandler;
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.csrf().disable();
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.oauth2Login()
        .successHandler(oAuthSuccessHandler)
        .userInfoEndpoint().userService(customOAuth2UserService)
        .and()
        .authorizationEndpoint()
        .baseUri("/api/oauth2/authentication");
    return http.build();
  }

}
