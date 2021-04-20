package com.shoppingapp.shoppingapp.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
@Value
public class Shopping_List {
    String user_id; // foreign key
}
