package me.max.moviesservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

/**
 * Created by amxik on 27.04.2019.
 */
@ControllerAdvice
public class ExceptionsAdvice {
    @ExceptionHandler(ConstraintViolationException.class)
    public @ResponseBody
    ResponseEntity<MyError> handle(Exception e) {

        MyError myError = new MyError();
        myError.setResponseCode(HttpStatus.BAD_REQUEST.value());
        myError.setMessage(e.getMessage());

        ResponseEntity<MyError> error = new ResponseEntity<>(myError, HttpStatus.BAD_REQUEST);

        return error;

    }
    }

