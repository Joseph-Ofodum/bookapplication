package com.example.bookapplication.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name= "review_tbl")
public class Reviews extends Base{
    private String comments;
    private Integer rating;
    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;

}
