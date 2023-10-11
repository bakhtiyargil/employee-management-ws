package az.baxtiyargil.employeemanagementws.error.model;

import org.springframework.http.HttpStatus;

/**
 * @author bakhtiyargil on 09.05.2021
 */
public class ErrorResponse {

    private final Integer code;
    private final String message;

    public ErrorResponse(HttpStatus status, String message) {
        this.code = status.value();
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
