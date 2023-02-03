package com.example.bookapplication.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class UserUtil {

    @Bean
    public static Optional<String> extractEmailFromPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken))
            return Optional.of(authentication.getName());
        return Optional.empty();
    }
}
