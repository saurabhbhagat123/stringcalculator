package com.exception;

public class InvalidExpressionException extends RuntimeException {

    private String message = "INVALID EXPRESSION";

    public String getMessage() {
        return message;
    }


}
