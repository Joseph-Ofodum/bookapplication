package com.example.bookapplication.enums;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public enum Role {
    ADMIN, USER, ANONYMOUS;


    public Collection<? extends GrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(this.name()));
        return authorities;
    }

}
