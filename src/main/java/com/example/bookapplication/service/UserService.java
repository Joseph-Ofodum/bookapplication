package com.example.bookapplication.service;

import com.example.bookapplication.dto.UserRegistrationRequest;
import com.example.bookapplication.dto.UserRegistrationResponse;
import com.example.bookapplication.entity.User;
import com.example.bookapplication.exception.AlreadyExistsException;
import com.example.bookapplication.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;


public interface UserService {
    UserRegistrationResponse saveUser(UserRegistrationRequest registrationDto) throws AlreadyExistsException, IOException;

    ApiResponse<Object> verifyRegistration(String token);


}
