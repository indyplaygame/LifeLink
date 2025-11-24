package dev.indy.lifelink.config;

import dev.indy.lifelink.auth.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final AuthInterceptor _authInterceptor;
    private final String[] _allowedOrigins;

    @Autowired
    public WebConfig(AuthInterceptor authInterceptor, @Value("${server.cors.allowed-origins}") String allowedOrigins) {
        this._authInterceptor = authInterceptor;
        this._allowedOrigins = allowedOrigins.split(";");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this._authInterceptor);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedHeaders("*")
            .allowedOrigins(this._allowedOrigins)
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowCredentials(true);
    }
}
