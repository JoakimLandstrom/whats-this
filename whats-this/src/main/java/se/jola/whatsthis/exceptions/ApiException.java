package se.jola.whatsthis.exceptions;

public class ApiException extends Exception {
    
    private static final long serialVersionUID = 3307157399383597721L;

    public ApiException(String message) {
	super(message);
    }

    public ApiException(String message, Throwable throwable) {
	super(message, throwable);
    }
}
