package suncertify.gui.controller;

public class CannotConnectToServerException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public CannotConnectToServerException() {
    }

    public CannotConnectToServerException(String message) {
        super(message);
    }

    public CannotConnectToServerException(String message,
            Throwable cause) {
        super(message, cause);
    }
}
