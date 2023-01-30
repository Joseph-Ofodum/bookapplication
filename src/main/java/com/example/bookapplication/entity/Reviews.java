package com.example.bookapplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name= "review-tbl")
public class Reviews extends Base{
    private String comments;

}
