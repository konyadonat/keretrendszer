package hu.uni.eku.tzs.service.exceptions;

public class RolesNotFoundException extends Exception {
    public RolesNotFoundException() {
    }

    public RolesNotFoundException(String message) {
        super(message);
    }

    public RolesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RolesNotFoundException(Throwable cause) {
        super(cause);
    }

    public RolesNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
