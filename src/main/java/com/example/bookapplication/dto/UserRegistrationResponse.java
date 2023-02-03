package com.example.bookapplication.dto;

import com.example.bookapplication.enums.Gender;
import lombok.Data;

@Data
public class UserRegistrationResponse {
    private String name;
    private String email;
    private Gender gender;
    private Boolean verificationStatus;

}
