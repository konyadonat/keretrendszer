package hu.uni.eku.tzs.service.exceptions;

public class ActorsNotFoundException extends Exception {

    public ActorsNotFoundException() {
    }

    public ActorsNotFoundException(String message) {
        super(message);
    }

    public ActorsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActorsNotFoundException(Throwable cause) {
        super(cause);
    }

    public ActorsNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
