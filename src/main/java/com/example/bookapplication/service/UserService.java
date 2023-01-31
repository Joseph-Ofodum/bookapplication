package com.example.bookapplication.service;

import com.example.bookapplication.dto.RegistrationRequest;
import com.example.bookapplication.exception.AlreadyExistsException;

import java.io.IOException;

public interface UserService {
    RegistrationRequest saveUser(RegistrationRequest registrationDto) throws AlreadyExistsException, IOException;
}
