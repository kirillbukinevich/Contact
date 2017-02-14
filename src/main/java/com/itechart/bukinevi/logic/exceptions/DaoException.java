package com.itechart.bukinevi.logic.exceptions;

public class DaoException extends RuntimeException {

    private static final long serialVersionUID = -2457861933931953482L;

    public DaoException() {
        super();
    }

    public DaoException(String message, Exception e) {
        super(message, e);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Exception e) {
        super(e);
    }

}
