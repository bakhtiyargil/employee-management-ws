package az.baxtiyargil.employeemanagementws.security.model;

import java.io.Serializable;

/**
 * @author bakhtiyargil on 08.05.2021
 */
public class JwtAuthenticationResponse implements Serializable {

    private final String token;


    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
