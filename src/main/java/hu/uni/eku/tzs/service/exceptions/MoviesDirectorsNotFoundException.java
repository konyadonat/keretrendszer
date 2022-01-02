package hu.uni.eku.tzs.service.exceptions;

public class MoviesDirectorsNotFoundException extends Exception {

    public MoviesDirectorsNotFoundException() {
    }

    public MoviesDirectorsNotFoundException(String message) {
        super(message);
    }

    public MoviesDirectorsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MoviesDirectorsNotFoundException(Throwable cause) {
        super(cause);
    }

    public MoviesDirectorsNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
