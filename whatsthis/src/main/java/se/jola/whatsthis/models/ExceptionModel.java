package se.jola.whatsthis.models;

import javax.ws.rs.core.Response.Status;

public final class ExceptionModel {

    private String exceptionMessage;

    private Status httpStatus;

    public ExceptionModel(String exceptionMessage, Status httpStatus){
        this.exceptionMessage = exceptionMessage;
        this.httpStatus = httpStatus;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public Status getHttpStatus() {
        return httpStatus;
    }

}
