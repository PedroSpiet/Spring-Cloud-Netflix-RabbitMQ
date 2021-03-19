package com.spiet.payments.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = -2589270442538540944L;

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}

