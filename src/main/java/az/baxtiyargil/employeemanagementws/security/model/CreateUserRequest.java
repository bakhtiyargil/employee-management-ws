package az.baxtiyargil.employeemanagementws.security.model;

import javax.validation.constraints.NotNull;

/**
 * @author bakhtiyargil on 08.05.2021
 */
public class CreateUserRequest {

    public CreateUserRequest() {
    }


    @NotNull
    private String username;

    @NotNull
    private String password;


    @NotNull
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

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
}
