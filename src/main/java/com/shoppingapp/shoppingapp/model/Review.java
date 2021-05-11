package com.shoppingapp.shoppingapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
//@Table(name="Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String review_id;

    String review_content;
    public Review() {

    }
}
