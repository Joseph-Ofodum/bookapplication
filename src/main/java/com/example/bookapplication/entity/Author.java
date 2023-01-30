package com.example.bookapplication.entity;


import com.example.bookapplication.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

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
}
