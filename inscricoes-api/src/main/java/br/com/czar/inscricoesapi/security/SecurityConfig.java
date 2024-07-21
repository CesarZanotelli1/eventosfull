package br.com.czar.inscricoesapi.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class SecurityConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthFilter())
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-ui/**", "/swagger-resources/**", "/v3/**");
    }
}
