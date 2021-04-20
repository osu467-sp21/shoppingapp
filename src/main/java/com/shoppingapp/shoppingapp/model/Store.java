package com.shoppingapp.shoppingapp.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
@Value
public class Store {
    String store_id;
    String name;
    String addr_line_1;
    String addr_line_2;
    String addr_line_3;
    String city;
    String state;
    Integer zip_code;
    String country;
}
