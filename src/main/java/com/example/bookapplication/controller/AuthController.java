package com.example.bookapplication.controller;

import com.example.bookapplication.dto.LoginRequest;
import com.example.bookapplication.entity.User;
import com.example.bookapplication.repository.UserRepository;
import com.example.bookapplication.security.tokens.TokenService;
import com.example.bookapplication.security.userDetails.AppUserDetailsService;
import com.example.bookapplication.service.UserService;
import com.example.bookapplication.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final TokenService tokenService;

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final AppUserDetailsService userDetailsService;

    private final UserService userService;


    @PostMapping("/login")
    public ApiResponse authenticate(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            UserDetails user = userDetailsService.loadUserByUsername(loginRequest.getEmail());
            if(!user.isEnabled())
                throw new UsernameNotFoundException("You have not been verified. Check your email to be verified!");
            if (!user.isAccountNonLocked()){
                return new ApiResponse<>("This account has been deactivated", false, null, HttpStatus.OK);
            }
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            if(authentication == null)
                throw new InvalidCredentialsException("Invalid Email or Password");
            return new ApiResponse<>("Login Successful",
                    true, tokenService.generateToken(authentication), HttpStatus.OK);
        } catch (InvalidCredentialsException e) {
            return new ApiResponse<>("Invalid Credentials", false, null, HttpStatus.UNAUTHORIZED);
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            return new ApiResponse<>("Password or Email not correct", false, null, HttpStatus.UNAUTHORIZED);
        }
    }

//    @PostMapping("/forgot-password-request")
//    public ResponseEntity<String> passwordRequestReset(@Valid @RequestBody ForgotPasswordRequestDto requestDto) throws IOException {
//        String result = userService.forgotPasswordRequest(requestDto);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//    @PostMapping("/reset-password/{token}")
//    public ResponseEntity<String> passwordReset(@PathVariable String token, @Valid @RequestBody PasswordResetDto passwordResetDto){
//        String result = userService.resetPassword(token, passwordResetDto);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//    @PutMapping("/update-password")
//    public ResponseEntity<ApiResponse<String>> updatePassword(@Valid  @RequestBody UpdatePasswordDto updatePasswordDto){
//        ApiResponse response = userService.updatePassword( updatePasswordDto);
//        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
//    }

}
