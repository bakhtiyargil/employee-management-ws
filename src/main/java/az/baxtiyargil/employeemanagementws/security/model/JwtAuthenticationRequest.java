package az.baxtiyargil.employeemanagementws.security.model;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author bakhtiyargil on 08.05.2021
 */
public class JwtAuthenticationRequest implements Serializable {

    public JwtAuthenticationRequest() {
    }

    public JwtAuthenticationRequest(@NotEmpty String username, @NotEmpty String password) {
        this.username = username;
        this.password = password;
    }

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "JwtAuthenticationRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
