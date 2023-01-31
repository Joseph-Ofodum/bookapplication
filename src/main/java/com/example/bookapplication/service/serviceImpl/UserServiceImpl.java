package com.example.bookapplication.service.serviceImpl;

import com.example.bookapplication.dto.RegistrationRequest;
import com.example.bookapplication.exception.AlreadyExistsException;
import com.example.bookapplication.repository.UserRepository;
import com.example.bookapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public RegistrationRequest saveUser(RegistrationRequest registrationDto) throws AlreadyExistsException, IOException {
   //     boolean emailExists= userRepository.
        return null;
    }
}
