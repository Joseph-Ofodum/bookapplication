package com.example.bookapplication.dto;

import com.example.bookapplication.enums.Gender;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class AuthorRequest {
    @NotNull
    private String name;
    private Gender gender;
    private String bio;
}
