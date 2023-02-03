package com.example.bookapplication.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_updated")
    private Date updatedAt;


    @PrePersist
    public void createdAt(){

        this.createdAt = new Date();
    }

    @PreUpdate
    public void updatedAt(){

        this.updatedAt = new Date();
    }

}
