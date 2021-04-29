package com.shoppingapp.shoppingapp.ShoppingList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.shoppingapp.shoppingapp.model.Product;
import com.shoppingapp.shoppingapp.model.Shopping_Info;
import com.shoppingapp.shoppingapp.model.Shopping_List;
import org.joda.time.IllegalFieldValueException;
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class ShoppingInfoExtractor {

    Gson gson = new Gson();

    public Shopping_Info processPayload(String payload) throws IllegalFieldValueException {
        System.out.println(payload);
        Shopping_Info shopping_info = gson.fromJson(payload, Shopping_Info.class);
        System.out.println(shopping_info);
        validateRequiredFields(shopping_info);
        return shopping_info;
    }

    public ArrayList<Product> processItems(ArrayList<Map<String, Map<String, Double>>> items) {
        ArrayList<Product> processedItems = new ArrayList<>();
        items.forEach(item-> {
                String name = retrieveName(item);
                JsonElement jsonItem = gson.toJsonTree(item.get(name));

                Product product = gson.fromJson(jsonItem, Product.class);
                product.setItem_name(name);
                processedItems.add(product);
            }
        );
        return processedItems;
    }

    private String retrieveName(Map<String, Map<String, Double>> singleItem) {
        for (String key: singleItem.keySet()) {
            // only key for the item is the name
            return key;
        }
        return null;
    }

    private void validateRequiredFields(Shopping_Info payload) throws IllegalFieldValueException {
        Optional.ofNullable(payload.getShoppingList()).orElseThrow(IllegalAccessError::new);
        Optional.ofNullable(payload.getSurroundDistance()).orElseThrow(IllegalAccessError::new);
        Optional.ofNullable(payload.getZipCode()).orElseThrow(IllegalAccessError::new);
    }
}
