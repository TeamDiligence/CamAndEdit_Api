package camandedit.server.global.config;

import camandedit.server.global.config.web.HttpLoggingFilter;
import camandedit.server.global.config.web.LoginUserArgumentResolver;
import java.util.List;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new LoginUserArgumentResolver());
    }

    @Bean
    public FilterRegistrationBean commonLoggingFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new HttpLoggingFilter());
        bean.setOrder(Integer.MIN_VALUE);
        return bean;
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000", "https://camandedit.com")
            .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE", "PATCH");
    }
}
