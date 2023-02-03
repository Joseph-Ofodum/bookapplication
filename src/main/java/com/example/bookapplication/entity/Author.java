package com.example.bookapplication.entity;


import com.example.bookapplication.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name="author_tbl")
public class Author extends Base{
    @NotNull
    private String name;
    private Gender gender;
    @Column(name="author_biography")
    private String bio;
    @JsonIgnore
    @OneToMany(mappedBy ="author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> book;

}
