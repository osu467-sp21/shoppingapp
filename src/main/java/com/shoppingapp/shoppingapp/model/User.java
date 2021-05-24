package com.shoppingapp.shoppingapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity
@AllArgsConstructor
@Table(name="User")
public class User {
    // UUID
    @Id
    @Column(length=128)
    String user_id;
    @Column(length=128)
    String username;
    Date signup_date;
    @Column(length=128)
    String first_name;
    @Column(length=128)
    String last_name;
    Integer zip_code;
    Integer master_shopper_level;
    @Column(length=128)
    String active_shopping_list; // foreign key

    public User() {}
}
