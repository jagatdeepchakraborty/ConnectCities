package com.mc.connectcities.exception;

public class ProcessingException extends Exception{
    private String message;

    public ProcessingException(String message) {
        super(message);
        this.message = message;
    }

    public ProcessingException(Throwable e, String message) {
        super(message, e);
        this.message = message;
    }
}
