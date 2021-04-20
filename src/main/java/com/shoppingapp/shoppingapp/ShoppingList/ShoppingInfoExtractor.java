package com.shoppingapp.shoppingapp.ShoppingList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingapp.shoppingapp.model.Shopping_Info;
import org.joda.time.IllegalFieldValueException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ShoppingInfoExtractor {

    public Shopping_Info processPayload(Map<String,Object> payload) {
        // map to Shopping_Info class
        // return shoppingMapper.convertValue(payload, Shopping_Info.class);
        return null;
    }

    private void validateRequiredFields(Map<String,Object> payload) throws IllegalFieldValueException {
    }
}
