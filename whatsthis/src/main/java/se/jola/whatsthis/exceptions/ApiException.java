package se.jola.whatsthis.exceptions;

public class ApiException extends Exception {

    private static final long serialVersionUID = -6109003831652959601L;

    public ApiException(String message){
        super(message);
    }

    public ApiException(String message, Exception e){
        super(message, e);
    }
}
