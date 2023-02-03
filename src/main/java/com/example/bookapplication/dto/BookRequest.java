package com.example.bookapplication.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BookRequest {
    private String title;
    private String description;
    private LocalDate publicationDate;
    private String imageUrl;
    private String genre;

}
