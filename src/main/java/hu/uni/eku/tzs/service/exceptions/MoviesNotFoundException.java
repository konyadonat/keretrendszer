package hu.uni.eku.tzs.service.exceptions;

public class MoviesNotFoundException extends Exception {

    public MoviesNotFoundException() {
    }

    public MoviesNotFoundException(String message) {
        super(message);
    }

    public MoviesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MoviesNotFoundException(Throwable cause) {
        super(cause);
    }

    public MoviesNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
