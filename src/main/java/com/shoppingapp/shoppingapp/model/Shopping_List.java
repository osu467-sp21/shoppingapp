package com.shoppingapp.shoppingapp.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import javax.persistence.Id;

@Data
@Builder
@Value
public class Shopping_List {
    @Id
    String shopping_list_id;
    String user_id; // foreign key
}
