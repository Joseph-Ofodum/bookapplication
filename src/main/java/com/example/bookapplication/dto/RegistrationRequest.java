package com.example.bookapplication.dto;

import com.example.bookapplication.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class RegistrationRequest {
    @NotNull(message = "This field cannot be null")
    private String name;
    @NotNull(message="Please enter a password")
    private String password;
    private Gender gender;
    @Email
    @NotNull(message = "please add a valid email address")
    private String email;
}
