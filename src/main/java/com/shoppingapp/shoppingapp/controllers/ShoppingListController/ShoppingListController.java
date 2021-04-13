package com.shoppingapp.shoppingapp.controllers.ShoppingListController;

import com.shoppingapp.shoppingapp.model.User;
import com.shoppingapp.shoppingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ShoppingListController {

    // RDS instance created with table Items
    @Autowired
    private UserRepository repository;

    @GetMapping(value = {"/", "/home"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String home() {
        return "home";
    }

    @PostMapping(value = {"/userIds"})
    @ResponseStatus(HttpStatus.OK)
    public void userIds() {
        // TODO:to add fields, add to model -> abstract away to @Builder
        // concern of immutability per object
        repository.save(new User());
    }

    @GetMapping(value = {"/userIds"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Iterable<User>  getUserIds() {
        return repository.findAll();
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
        // TODO: add logic for insert single item into RDS
        // basically a retrieve and update
        return HttpStatus.ACCEPTED;
    }
}
