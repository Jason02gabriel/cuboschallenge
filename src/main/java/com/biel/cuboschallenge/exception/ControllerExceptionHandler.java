package com.biel.cuboschallenge.exception;

import com.biel.cuboschallenge.dto.ExceptionDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final String ID_NOT_FOUND_MESSAGE = "Client not found";
    private static final String NO_DATA_FOUND_MESSAGE = "No data can no longer be necessary";

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchElementException(NoSuchElementException e) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(ID_NOT_FOUND_MESSAGE, HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(NullableException.class)
    public ResponseEntity handleNullableException(NullableException e) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(NO_DATA_FOUND_MESSAGE, HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(NO_DATA_FOUND_MESSAGE, HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.badRequest().body(exceptionDTO);
    }


}

