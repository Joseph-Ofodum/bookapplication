package com.example.bookapplication.entity;

import com.example.bookapplication.enums.Gender;
import com.example.bookapplication.enums.Role;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Builder
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user_tbl")


public class User extends Base{

    private String name;

    private String password;

    private Boolean verificationStatus;

    private boolean isActive;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @NotNull
    private Role role;

    @Column(unique=true, nullable=false)
    private String email;

}
