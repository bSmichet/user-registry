package fr.airfrance.userregistry.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * Custom Exception used for user validation problems.
 */
public class UserValidationException extends NestedRuntimeException {

    public UserValidationException(String msg) {
        super(msg);
    }
}
