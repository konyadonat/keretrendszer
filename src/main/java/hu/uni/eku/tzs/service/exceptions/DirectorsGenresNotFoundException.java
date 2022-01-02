package hu.uni.eku.tzs.service.exceptions;

public class DirectorsGenresNotFoundException extends Exception {

    public DirectorsGenresNotFoundException() {
    }

    public DirectorsGenresNotFoundException(String message) {
        super(message);
    }

    public DirectorsGenresNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DirectorsGenresNotFoundException(Throwable cause) {
        super(cause);
    }

    public DirectorsGenresNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
