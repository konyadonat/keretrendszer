package hu.uni.eku.tzs.service.exceptions;

public class DirectorsNotFoundException extends Exception {
    public DirectorsNotFoundException() {
    }

    public DirectorsNotFoundException(String message) {
        super(message);
    }

    public DirectorsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DirectorsNotFoundException(Throwable cause) {
        super(cause);
    }

    public DirectorsNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
