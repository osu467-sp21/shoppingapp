package com.shoppingapp.shoppingapp.ShoppingList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shoppingapp.shoppingapp.model.Shopping_Info;
import com.shoppingapp.shoppingapp.model.Shopping_List;
import org.joda.time.IllegalFieldValueException;
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ShoppingInfoExtractor {

    public Shopping_Info processPayload(String payload) throws IllegalFieldValueException {
        Gson gson = new Gson();
        Shopping_Info shopping_info = gson.fromJson(payload, Shopping_Info.class);
        validateRequiredFields(shopping_info);
        return shopping_info;
    }

    private void validateRequiredFields(Shopping_Info payload) throws IllegalFieldValueException {
        Optional.ofNullable(payload.getShoppingList()).orElseThrow(IllegalAccessError::new);
        Optional.ofNullable(payload.getSurroundDistance()).orElseThrow(IllegalAccessError::new);
        Optional.ofNullable(payload.getZipCode()).orElseThrow(IllegalAccessError::new);
    }
}
