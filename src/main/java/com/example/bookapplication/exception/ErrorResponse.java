package com.example.bookapplication.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private String debugMessage;
    private HttpStatus status;
}
