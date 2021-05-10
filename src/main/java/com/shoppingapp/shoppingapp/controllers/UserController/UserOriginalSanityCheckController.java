package com.shoppingapp.shoppingapp.controllers.UserController;

import com.shoppingapp.shoppingapp.model.UserOriginalSanityCheck;
import com.shoppingapp.shoppingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController(value="/usersSanityCheckController")
public class UserOriginalSanityCheckController {

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
//        repository.save(new UserOriginalSanityCheck());
    }

//    @GetMapping(value = {"/userIds"})
//    @ResponseStatus(HttpStatus.OK)
//    public @ResponseBody
//    Iterable<UserOriginalSanityCheck>  getUserIds() {
//        return repository.findAll();
//    }
}
