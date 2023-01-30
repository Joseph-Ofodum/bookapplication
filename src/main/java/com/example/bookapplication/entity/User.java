package com.example.bookapplication.entity;

import com.example.bookapplication.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

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

    private Gender gender;

    @Column(unique=true, nullable=false)
    private String email;
}
