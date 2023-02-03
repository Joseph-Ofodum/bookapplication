package com.example.bookapplication.entity;

import com.example.bookapplication.enums.TokenStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "token_tbl")
@Getter
@Setter
public class Token extends Base{
    @Column(length = 500)
    private String token;

    @Enumerated(EnumType.STRING)
    private TokenStatus tokenStatus;

    @OneToOne()
    @JoinColumn(name = "user_tbl_id")
    private User user;
}


