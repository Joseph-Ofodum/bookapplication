package com.example.bookapplication.security.userDetails;

import com.example.bookapplication.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Data
@Builder
@Getter
@Setter
public class AppUserDetails implements UserDetails {

    private User user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return user.getRole().getGrantedAuthorities();
    }

    @Override
    public String getPassword() {

        return user.getPassword();
    }

    @Override
    public String getUsername() {

        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {


        return user.isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getVerificationStatus();
    }
}


