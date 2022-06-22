package com.sztorma.rest.webservices.restfulwebservices.exception;

import java.util.Date;

public class ExceptionResponse extends FallbackResponse {

    private String details;

    public ExceptionResponse(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public String getDetails() {
        return details;
    }
}
