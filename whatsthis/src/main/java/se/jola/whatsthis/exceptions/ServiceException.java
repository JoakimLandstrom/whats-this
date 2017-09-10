package se.jola.whatsthis.exceptions;

import se.jola.whatsthis.models.ExceptionModel;

public class ServiceException extends RuntimeException {

    private ExceptionModel exceptionModel;

    public ServiceException(ExceptionModel exceptionModel){
        super(exceptionModel.getExceptionMessage());
        this.exceptionModel = exceptionModel;
    }

    public ServiceException(ExceptionModel exceptionModel, Throwable throwable){
        super(exceptionModel.getExceptionMessage(), throwable);
        this.exceptionModel = exceptionModel;
    }

    public ExceptionModel getExceptionModel() {
        return exceptionModel;
    }
}
