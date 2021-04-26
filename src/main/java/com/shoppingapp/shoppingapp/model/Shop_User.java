package com.shoppingapp.shoppingapp.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.Date;

@Data
@Builder
@Value
public class Shop_User {
    String user_id;
    String username;
    String first_name;
    String last_name;
    String email;
    Integer zip_code;
    Date signup_date;
    String master_shopper_level;
    String active_shopping_list; // foreign key
}
