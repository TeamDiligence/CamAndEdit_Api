package camandedit.server.global.security;


import camandedit.server.global.security.filter.JwtAuthenticationFilter;
import camandedit.server.global.security.filter.JwtExceptionFilter;
import camandedit.server.global.security.jwt.JwtResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{

  private final JwtResolver jwtResolver;

  @Override
  public void configure(HttpSecurity http) throws Exception {
    JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtResolver);
    JwtExceptionFilter jwtExceptionFilter = new JwtExceptionFilter();
    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    http.addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter.class);
  }
}
