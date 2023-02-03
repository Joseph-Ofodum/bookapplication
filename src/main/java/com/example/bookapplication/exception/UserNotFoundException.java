package com.example.bookapplication.exception;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserNotFoundException extends RuntimeException{
    private String debugMessage;
}
