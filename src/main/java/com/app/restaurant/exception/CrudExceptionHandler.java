package com.app.restaurant.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@Log4j2
@ControllerAdvice
public class CrudExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * @param servletRequest
     * @returns "Not found" when entity with given id is not found
     */
    @ExceptionHandler(RestaurantManagerException.class)
    public ResponseEntity<Object> handleIDNotFoundException(HttpServletRequest servletRequest) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("url: ", servletRequest.getRequestURI());
        body.put("method used: ", servletRequest.getMethod());
        body.put("message: ", "Entity with this id was not found!");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * @param servletRequest
     * @returns "Already exists" when entity with given name was found
     */
    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<Object> handleExistingEntityException(HttpServletRequest servletRequest) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("url: ", servletRequest.getRequestURI());
        body.put("method used: ", servletRequest.getMethod());
        body.put("message: ", "Entity with this name was found! Please insert a different item.");

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
}
