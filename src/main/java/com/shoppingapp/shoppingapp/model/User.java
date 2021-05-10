package com.shoppingapp.shoppingapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@Table(name="User")
public class User {
    // UUID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long user_id;
    String firstName;
    String lastName;
    String email;
    String login;
    String mobilePhone;

    public User() {}
}
