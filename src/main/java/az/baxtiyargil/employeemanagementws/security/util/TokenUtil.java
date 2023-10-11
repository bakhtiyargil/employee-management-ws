package az.baxtiyargil.employeemanagementws.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.DefaultClock;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bakhtiyargil on 08.05.2021
 */
public class TokenUtil {

    private static final Clock clock = DefaultClock.INSTANCE;

    public TokenUtil() {
    }


    public static Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(JwtConstants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public static String generateToken(String username, List<String> roleList) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roleList);

        Date createdDate = clock.now();
        Date expirationDate = new Date(createdDate.getTime() + JwtConstants.TOKEN_EXPIRATION);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(createdDate)
                .setAudience(JwtConstants.TOKEN_AUDIENCE)
                .setIssuer(JwtConstants.TOKEN_ISSUER)
                .setHeaderParam("typ", JwtConstants.TOKEN_TYPE)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, JwtConstants.JWT_SECRET)
                .compact();
    }

    public static boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JwtConstants.JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (ExpiredJwtException exception) {
            System.out.printf("Expired JWT token: %s",
                    authToken, exception.getMessage());
        } catch (UnsupportedJwtException exception) {
            System.out.printf("Unsupported JWT token: %s",
                    authToken, exception.getMessage());
        } catch (MalformedJwtException exception) {
            System.out.printf("Invalid JWT token: %s",
                    authToken, exception.getMessage());
        } catch (SignatureException exception) {
            System.out.printf("JWT token with invalid signature : %s",
                    authToken, exception.getMessage());
        } catch (IllegalArgumentException exception) {
            System.out.printf("Empty JWT token: %s",
                    authToken, exception.getMessage());
        }
        return false;
    }
}
