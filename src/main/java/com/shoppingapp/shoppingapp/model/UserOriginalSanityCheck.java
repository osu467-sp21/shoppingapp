package com.shoppingapp.shoppingapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="usersIdTest")
public class UserOriginalSanityCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 64)
    String name;
}
