package com.sztorma.rest.webservices.restfulwebservices.user;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

}
