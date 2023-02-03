package com.example.bookapplication.security.userDetails;

import com.example.bookapplication.entity.User;
import com.example.bookapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User dbUser = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Not Found"));
        return new AppUserDetails(dbUser);
    }
}
