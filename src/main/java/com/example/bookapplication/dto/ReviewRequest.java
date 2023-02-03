package com.example.bookapplication.dto;

import com.example.bookapplication.entity.Book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class ReviewRequest {
    private String comments;
    private Integer rating;
    @NotNull
    private Book book;
}
