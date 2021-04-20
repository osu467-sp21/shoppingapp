package com.shoppingapp.shoppingapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
@Value
@AllArgsConstructor
public class Shopping_Info {
    Integer zip_code;
}
