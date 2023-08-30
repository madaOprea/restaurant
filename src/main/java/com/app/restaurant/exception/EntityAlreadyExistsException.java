package com.app.restaurant.exception;


import javax.persistence.EntityExistsException;

public class EntityAlreadyExistsException extends EntityExistsException {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
