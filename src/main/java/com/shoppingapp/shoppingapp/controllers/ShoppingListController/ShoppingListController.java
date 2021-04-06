package com.shoppingapp.shoppingapp.controllers.ShoppingListController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingListController {

    @GetMapping(value={"/", "/home"})
    public String home() {
        return "home";
    }
}
