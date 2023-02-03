package com.example.bookapplication.service.serviceImpl;

import com.example.bookapplication.dto.UserRegistrationRequest;
import com.example.bookapplication.dto.UserRegistrationResponse;
import com.example.bookapplication.entity.Token;
import com.example.bookapplication.entity.User;
import com.example.bookapplication.enums.Gender;
import com.example.bookapplication.enums.Role;
import com.example.bookapplication.exception.AlreadyExistsException;
import com.example.bookapplication.exception.InvalidTokenException;
import com.example.bookapplication.exception.UserNotFoundException;
import com.example.bookapplication.repository.TokenRepository;
import com.example.bookapplication.repository.UserRepository;
import com.example.bookapplication.security.JavaMailConfig;
import com.example.bookapplication.security.tokens.TokenService;
import com.example.bookapplication.service.JavaMailService;
import com.example.bookapplication.service.UserService;
import com.example.bookapplication.utils.ApiResponse;
import com.example.bookapplication.utils.ResponseManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.example.bookapplication.enums.TokenStatus.ACTIVE;
import static com.example.bookapplication.enums.TokenStatus.EXPIRED;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final TokenRepository tokenRepository;
    private final ResponseManager responseManager;
    private final HttpServletRequest request;
    private final JavaMailService javaMailService;

    @Override
    public UserRegistrationResponse saveUser(UserRegistrationRequest registrationDto) throws AlreadyExistsException, IOException {
        boolean emailExists = userRepository.existsByEmail(registrationDto.getEmail());
        if (emailExists) throw new AlreadyExistsException("This Email is already registered");
        User users = User.builder()
                .name(registrationDto.getName())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .gender(Gender.valueOf(registrationDto.getGender().toUpperCase()))
                .email(registrationDto.getEmail())
                .role(Role.USER)
                .isActive(true)
                .build();
        userRepository.save(users);

        String validToken = tokenService.generateVerificationToken(registrationDto.getEmail());
        Token token = new Token();
        token.setToken(validToken);
        token.setTokenStatus(ACTIVE);
        token.setUser(users);
        tokenRepository.save(token);
        javaMailService.sendMail(registrationDto.getEmail(),
                "Verify your email address",
                "Hi " + users.getName() + ", Thank you for your interest in joining our book club." +

                        "To complete your registration, we need you to verify your email address \n" + "http://" + request.getServerName() + ":" + request.getServerPort() + "/api/v1/user/verifyRegistration?token=" + validToken);
        UserRegistrationResponse registrationResponse = new UserRegistrationResponse();
        BeanUtils.copyProperties(users, registrationResponse);
        return registrationResponse;
    }

    @Override
    public ApiResponse<Object> verifyRegistration(String token) {

        Token verificationToken = tokenRepository.findByToken(token).orElseThrow(
                () -> new InvalidTokenException("Token Not Found"));

        if (verificationToken.getTokenStatus().equals(EXPIRED))
            throw new InvalidTokenException("Token already used");

        verificationToken.getUser().setVerificationStatus(true);
        verificationToken.setTokenStatus(EXPIRED);
        tokenRepository.save(verificationToken);
        return responseManager.success("Account verification successful");

    }
}
