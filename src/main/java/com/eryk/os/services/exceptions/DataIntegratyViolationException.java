package com.eryk.os.services.exceptions;

import java.io.Serial;

public class DataIntegratyViolationException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public DataIntegratyViolationException(String message) {
        super(message);
    }

    public DataIntegratyViolationException(String message, Throwable cause) {
        super(message, cause);
    }


}
