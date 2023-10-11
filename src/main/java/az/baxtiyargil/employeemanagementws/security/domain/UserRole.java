package az.baxtiyargil.employeemanagementws.security.domain;

/**
 * @author bakhtiyargil on 08.05.2021
 */

import java.util.stream.Stream;

public enum UserRole {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private String name;

    UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static UserRole of(String roleName) {
        return Stream.of(UserRole.values()).map(UserRole::getName)
                .filter(s -> s.equals(roleName)).map(UserRole::valueOf)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}