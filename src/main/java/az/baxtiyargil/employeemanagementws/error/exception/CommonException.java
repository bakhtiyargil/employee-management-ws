package az.baxtiyargil.employeemanagementws.error.exception;

/**
 * @author bakhtiyargil on 09.05.2021
 */
public class CommonException extends RuntimeException {

    private final String message;

    public CommonException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
