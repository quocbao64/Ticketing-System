package com.programing.exception;

public class NotFoundException extends RuntimeException {
    private int errorCode;
    public NotFoundException(int errorCode , String message){
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
