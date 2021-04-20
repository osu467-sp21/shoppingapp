package com.shoppingapp.shoppingapp.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
@Value
public class Store_Review {
    String store_id; // foreign key
    String user_id; // foreign key
    String comment;
    String rating;
}
