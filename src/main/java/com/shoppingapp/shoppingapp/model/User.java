package com.shoppingapp.shoppingapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="usersIdTest")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 64)
    int id;
}
