package camandedit.server.global.security;

import camandedit.server.global.security.filter.JwtAuthenticationFilter;
import camandedit.server.global.security.filter.JwtExceptionFilter;
import camandedit.server.global.security.handler.JwtAuthenticationEntryPoint;
import camandedit.server.global.security.handler.OAuthSuccessHandler;
import camandedit.server.global.security.jwt.JwtResolver;
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
  private final JwtResolver jwtResolver;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    //설정
    http.csrf().disable();
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.exceptionHandling()
        .authenticationEntryPoint(jwtAuthenticationEntryPoint);

    // 인증 허용
    http.authorizeRequests()
        .antMatchers("/api/**").authenticated()
        .and()
        .apply(new JwtSecurityConfig(jwtResolver));

    //OAuth
    http.oauth2Login()
        .successHandler(oAuthSuccessHandler)
        .userInfoEndpoint().userService(customOAuth2UserService)
        .and()
        .authorizationEndpoint()
        .baseUri("/oauth2/authentication");
    return http.build();
  }

}
