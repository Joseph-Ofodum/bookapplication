package com.example.bookapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> alreadyExist(AlreadyExistsException ne){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ne.getMessage());
        errorResponse.setDebugMessage("Already exist");
        errorResponse.setStatus(HttpStatus.CONFLICT);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> NotRegistered(UserNotFoundException ne){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ne.getMessage());
        errorResponse.setDebugMessage(ne.getDebugMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> tokenNotFound(InvalidTokenException ne) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ne.getMessage());
        errorResponse.setDebugMessage("Token not found");
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

}
