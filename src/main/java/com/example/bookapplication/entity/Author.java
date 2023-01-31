package com.example.bookapplication.entity;


import com.example.bookapplication.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name="author_tbl")
public class Author extends Base{
    private String name;

    private Gender gender;

    @Column(name="author_biography")
    private String bio;

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
