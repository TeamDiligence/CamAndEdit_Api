package camandedit.server.global.property;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("redirect-url")
public class RedirectUrl {

    private String oauth;
    private String invite;
}
