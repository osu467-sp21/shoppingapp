package com.shoppingapp.shoppingapp.model;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.Map;

@Data
@Value
@AllArgsConstructor
public class Shopping_Info {
    @JsonProperty("zipCode")
    Integer zipCode;
    @JsonProperty("surroundDistance")
    Float surroundDistance;
    @JsonProperty("shoppingList")
    ArrayList<Map<String, Map<String, Double>>> shoppingList;
}
