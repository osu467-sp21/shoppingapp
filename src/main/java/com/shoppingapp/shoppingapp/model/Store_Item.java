package com.shoppingapp.shoppingapp.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
@Value
public class Store_Item {
    String store_id; // foreign key
    String product_id; // foreign key
}
