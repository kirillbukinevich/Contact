package com.itechart.bukinevi.logic.exceptions;

public class IncorrectDataException extends RuntimeException {

    private static final long serialVersionUID = -2457861933931953482L;

    public IncorrectDataException() {
        super();
    }

    public IncorrectDataException(String message, Exception e) {
        super(message, e);
    }

    public IncorrectDataException(String message) {
        super(message);
    }

    public IncorrectDataException(Exception e) {
        super(e);
    }

}
