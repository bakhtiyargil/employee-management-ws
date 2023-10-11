package az.baxtiyargil.employeemanagementws.security.auth;

import az.baxtiyargil.employeemanagementws.security.util.JwtConstants;
import az.baxtiyargil.employeemanagementws.security.util.TokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author bakhtiyargil on 08.05.2021
 */
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        var authResult = getAuthentication(request);
        if (Objects.isNull(authResult)) {
            filterChain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authResult);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String jwtToken = extractJwtFromHeader(request);

        if (Objects.nonNull(jwtToken) && TokenUtil.validateJwtToken(jwtToken)) {
            Claims claims = TokenUtil.getAllClaimsFromToken(jwtToken);
            String username = claims.getSubject();

            var roles = ((List<String>) claims.get("roles")).stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            return new UsernamePasswordAuthenticationToken(username, null, roles);
        }
        return null;
    }

    private String extractJwtFromHeader(HttpServletRequest request) {
        String authHeaderVal = request.getHeader(JwtConstants.TOKEN_HEADER);

        if (authHeaderVal != null && authHeaderVal.startsWith(JwtConstants.TOKEN_PREFIX)) {
            return authHeaderVal.replace(JwtConstants.TOKEN_PREFIX, "");
        }
        return null;
    }
}
