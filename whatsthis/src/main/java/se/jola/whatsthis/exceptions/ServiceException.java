package se.jola.whatsthis.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 7130457633105877568L;

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message, Throwable throwable){
       super(message,throwable);
    }
}
