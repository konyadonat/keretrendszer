package hu.uni.eku.tzs.service.exceptions;

public class MoviesDirectorsAlreadyExistsException extends  Exception {

    public MoviesDirectorsAlreadyExistsException() {
    }

    public MoviesDirectorsAlreadyExistsException(String message) {
        super(message);
    }

    public MoviesDirectorsAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public MoviesDirectorsAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public MoviesDirectorsAlreadyExistsException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
