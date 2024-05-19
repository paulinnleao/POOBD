package com.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ResourceDependsOnAnotherEntityException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceDependsOnAnotherEntityException(String message) {
        super(message);
    }
}
