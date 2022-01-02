package hu.uni.eku.tzs.service.exceptions;

public class MoviesGenresNotFoundException extends Exception {

    public MoviesGenresNotFoundException() {
    }

    public MoviesGenresNotFoundException(String message) {
        super(message);
    }

    public MoviesGenresNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MoviesGenresNotFoundException(Throwable cause) {
        super(cause);
    }

    public MoviesGenresNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
