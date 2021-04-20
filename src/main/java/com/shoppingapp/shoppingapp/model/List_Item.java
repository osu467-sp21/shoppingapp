package com.shoppingapp.shoppingapp.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
@Value
public class List_Item {
    String shopping_list_id; // foreign key
    String product_id; // foreign key
}
