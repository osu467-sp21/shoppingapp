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
    String user_id;
    String username;
    Date signup_date;
    String first_name;
    String last_name;
    Integer zip_code;
    Integer master_shopper_level;
    String active_shopping_list; // foreign key

    public User() {}
}
