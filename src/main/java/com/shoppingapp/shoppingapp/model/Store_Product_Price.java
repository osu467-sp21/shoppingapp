package com.shoppingapp.shoppingapp.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
@Value
public class Store_Product_Price {
    String store_id; // foreign key
    String product_id; // foreign key
    String price_id;
}
