package christmas.util.exceptions;

import static christmas.util.exceptions.Exceptions.AUTHENTICATION_INVALID;

public class UnauthorizedAccessException extends RuntimeException{
    public UnauthorizedAccessException() {
        super(AUTHENTICATION_INVALID.getMessage());
    }
}
