package com.itechart.bukinevi.logic.exceptions;

public class AccessException extends RuntimeException {

    private static final long serialVersionUID = 993938898346970180L;

    public AccessException() {
        super();
    }

    public AccessException(String message, Exception e) {
        super(message, e);
    }

    public AccessException(String message) {
        super(message);
    }
}
