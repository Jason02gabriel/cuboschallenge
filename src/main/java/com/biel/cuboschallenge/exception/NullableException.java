package com.biel.cuboschallenge.exception;

public class NullableException extends RuntimeException {

    public NullableException(String msg){
        super(msg);
    }

    public NullableException(String msg, Throwable cause){
        super(msg, cause);
    }
}
