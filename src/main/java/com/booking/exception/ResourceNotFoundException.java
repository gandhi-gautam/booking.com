package com.booking.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String messgae) {
        super(messgae);
    }
}
