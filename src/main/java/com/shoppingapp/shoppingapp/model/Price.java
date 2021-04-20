package com.shoppingapp.shoppingapp.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.Date;

@Data
@Builder
@Value
public class Price {
    String price_id;
    String user_id; // foreign key
    Integer sale;
    Date date_entered;
    Date sale_expiration;
}
