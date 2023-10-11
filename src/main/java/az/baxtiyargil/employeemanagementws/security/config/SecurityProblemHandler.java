package az.baxtiyargil.employeemanagementws.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bakhtiyargil on 09.05.2021
 */
@Component
public class SecurityProblemHandler implements AuthenticationEntryPoint, AccessDeniedHandler {
    private final HandlerExceptionResolver exceptionResolver;

    @Autowired
    public SecurityProblemHandler(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver) {
        this.exceptionResolver = exceptionResolver;
    }

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        this.exceptionResolver.resolveException(request, response, null, exception);
    }

    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) {
        this.exceptionResolver.resolveException(request, response, null, exception);
    }
}
