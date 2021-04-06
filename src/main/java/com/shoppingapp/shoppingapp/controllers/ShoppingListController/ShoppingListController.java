package com.shoppingapp.shoppingapp.controllers.ShoppingListController;

import com.amazonaws.services.rds.AmazonRDSClientBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ShoppingListController {

    // would add into an aws builder after test connection

    @GetMapping(value = {"/", "/home"})
    @ResponseStatus(HttpStatus.OK)
    public String home() {
        AmazonRDSClientBuilder clientBuilder;
        // RDS instance created with table Items
        return "home";
    }

    @PostMapping("/compare")
    public String compareShoppingList(@RequestBody(required=true) Map<String, Object>  payload) {
        return payload.toString();
    }
}
