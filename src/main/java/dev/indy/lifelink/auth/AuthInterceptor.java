package dev.indy.lifelink.auth;

import dev.indy.lifelink.exception.HttpException;
import dev.indy.lifelink.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    private final AuthService _authService;

    @Autowired
    public AuthInterceptor(AuthService authService) {
        this._authService = authService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod method) {
            AuthRequired annotation = method.getMethodAnnotation(AuthRequired.class);
            if(annotation == null && !method.getBeanType().isAnnotationPresent(AuthRequired.class)) return true;

            AuthMethod authMethod = annotation.value();
            switch(authMethod) {
                case SESSION:
                    HttpSession session = request.getSession(false);
                    if(!this._authService.isAuthenticated(session))
                        throw new HttpException(HttpStatus.UNAUTHORIZED, "This endpoint requires authentication.");

                    break;
                case TOKEN:
                    break;
            }
        }

        return true;
    }
}
