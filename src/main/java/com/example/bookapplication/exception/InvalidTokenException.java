package com.example.bookapplication.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InvalidTokenException extends RuntimeException{
    private String debugMessage;

    public InvalidTokenException(String message) {

        super(message);
    }

    public InvalidTokenException(String message, String debugMessage) {
        super(message);
        this.debugMessage = debugMessage;
    }
}
