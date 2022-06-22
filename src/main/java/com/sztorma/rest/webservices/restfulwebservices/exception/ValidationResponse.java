package com.sztorma.rest.webservices.restfulwebservices.exception;

import org.springframework.validation.ObjectError;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ValidationResponse extends FallbackResponse {

    private Map<String, String> invalidParams;

    public ValidationResponse(Date timestamp, String message, List<ObjectError> objectErrors) {
        this.timestamp = timestamp;
        this.message = message;
        this.invalidParams = convertInvalidMessages(objectErrors);
    }

    public Map<String, String> getInvalidParams() {
        return invalidParams;
    }

    private Map<String, String> convertInvalidMessages(List<ObjectError> errors) {
        return errors.stream()
            .collect(Collectors.toMap(error -> subStringAfterLastDotOccurence(error.getCodes()[0]),
                ObjectError::getDefaultMessage));
    }

    private String subStringAfterLastDotOccurence(String text) {
        return text.substring(text.lastIndexOf('.') + 1);
    }
}
