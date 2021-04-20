package com.shoppingapp.shoppingapp.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
@Value
public class Item {
    // UUID
    String item_id;
    Integer item_number;
    Integer check_digit;
    String item_name;
    Integer size_unit;
    String generic_product; //(foreign key)
}
