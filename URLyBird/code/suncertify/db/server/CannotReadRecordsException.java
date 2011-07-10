package suncertify.db.server;

public class CannotReadRecordsException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public CannotReadRecordsException() {
        super();
    }

    public CannotReadRecordsException(String message) {
        super(message);
    }

    public CannotReadRecordsException(String message,
            Throwable cause) {
        super(message, cause);
    }

}
