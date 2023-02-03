package com.example.bookapplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "book_tbl")
public class Book extends Base{
    private String title;
    private String description;
    @Column(name="publication_date")
    private LocalDate publicationDate;
    @Column(name="imageurl")
    private String imageUrl;
    private String genre;
    @JsonIgnore
    @OneToMany(mappedBy ="book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Reviews> reviews;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;


}
