package com.example.bookapplication.controller;

import com.example.bookapplication.dto.UserRegistrationRequest;
import com.example.bookapplication.exception.AlreadyExistsException;
import com.example.bookapplication.service.UserService;
import com.example.bookapplication.utils.ApiResponse;
import com.example.bookapplication.utils.ResponseManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor@RequestMapping("/api/v1")
public class UserController {
    private final ResponseManager responseManager;

    private final UserService userService;


    @PostMapping("/user/signup")
    public ResponseEntity<ApiResponse> signup(@Valid @RequestBody UserRegistrationRequest request) throws AlreadyExistsException, IOException {
        userService.saveUser(request);
        return new ResponseEntity<>(responseManager.success("Registration Successful! Check your mail for activation link"), HttpStatus.CREATED);
    }

    @GetMapping("/user/verifyRegistration")
    public ResponseEntity<ApiResponse> verifyAccount(@RequestParam("token") String token){
        return new ResponseEntity<> (userService.verifyRegistration(token), HttpStatus.CREATED);
    }

}
