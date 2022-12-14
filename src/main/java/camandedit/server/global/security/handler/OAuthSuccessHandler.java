package camandedit.server.global.security.handler;

import camandedit.server.global.property.RedirectUrl;
import camandedit.server.global.security.dto.OAuthUserPrincipal;
import camandedit.server.user.domain.service.TokenProvider;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;
    private final RedirectUrl redirectUrlProperty;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
        OAuthUserPrincipal userPrincipal = (OAuthUserPrincipal) authentication.getPrincipal();
        String accessToken = tokenProvider.createToken(userPrincipal.getUser());

        response.setStatus(HttpStatus.OK.value());
        response.sendRedirect(redirectUrlProperty.getOauth() + accessToken);
    }

}
