package se.jola.whatsthis.exceptions;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 7130457633105877568L;

    public ServiceException(String message) {
	super(message);
    }

    public ServiceException(String message, Throwable throwable) {
	super(message, throwable);
    }

}
