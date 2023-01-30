package com.example.bookapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @GeneratedValue
    private UUID uuid;

}
