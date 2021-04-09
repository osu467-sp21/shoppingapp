package com.shoppingapp.shoppingapp.controllers.ShoppingListController;

import com.amazonaws.services.rds.AmazonRDSClientBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ShoppingListController {

    // would add into an aws builder after test connection
    AmazonRDSClientBuilder clientBuilder;
    // RDS instance created with table Items

    @GetMapping(value = {"/", "/home"})
    @ResponseStatus(HttpStatus.OK)
    public String home() {
        return "home";
    }

    // request should contain a JSON object representing the shopping list
    @PostMapping(value = "/shoppingList")
    public String compareShoppingList(@RequestBody(required=true) Map<String, Object>  payload) {
        // handler for querying, extracting, and comparing the items
        return payload.toString();
    }

    // Update and delete mapping for a shopping list should occur at the frontend
    @PostMapping(value = "/userAddition")
    public HttpStatus userAdditionItem() {
        // add logic for insert into rds
        return HttpStatus.ACCEPTED;
    }
}
