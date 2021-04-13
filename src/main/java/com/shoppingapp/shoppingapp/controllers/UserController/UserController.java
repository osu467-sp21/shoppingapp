package com.shoppingapp.shoppingapp.controllers.UserController;

import com.shoppingapp.shoppingapp.model.User;
import com.shoppingapp.shoppingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController(value="/users")
public class UserController {

    // RDS instance created with table Items
    @Autowired
    private UserRepository repository;

    // Update and delete mapping for a shopping list should occur at the frontend
    @PostMapping(value = "/userAddition")
    public HttpStatus userAdditionItem() {
        // TODO: add logic for insert single item into RDS
        // basically a retrieve and update
        return HttpStatus.ACCEPTED;
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
    public @ResponseBody
    Iterable<User>  getUserIds() {
        return repository.findAll();
    }
}
