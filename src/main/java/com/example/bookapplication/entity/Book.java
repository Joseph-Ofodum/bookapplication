package com.example.bookapplication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


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
    private  String author;
    private String description;
    @Column(name="publication_date")
    private LocalDate publicationDate;
    @Column(name="imageurl")
    private String imageUrl;
//    private List<String> reviews;
}
