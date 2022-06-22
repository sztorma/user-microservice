package com.sztorma.rest.webservices.restfulwebservices.exception;

import java.util.Date;

public abstract class FallbackResponse {

    protected Date timestamp;

    protected String message;

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
}
