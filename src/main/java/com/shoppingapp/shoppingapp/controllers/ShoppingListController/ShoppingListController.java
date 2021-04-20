package com.shoppingapp.shoppingapp.controllers.ShoppingListController;

import com.shoppingapp.shoppingapp.ShoppingList.ShoppingComparison;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
public class ShoppingListController {

    private ShoppingComparison shoppingListComparison;

    @GetMapping(value = {"/", "/home"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String home() {
        return "home";
    }

    // request should contain a JSON object representing the shopping list
    @PostMapping(value = "/shoppingList")
    public ResponseEntity<?> compareShoppingList(@RequestBody(required=true) Map<String, Object>  payload) {
        // handler for querying, extracting, and comparing the items
        // TODO: add in a model that represents an Item + ShoppingList

        // TODO: call shoppingListComparison.getChosenList(payload.shoppinglist);
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

}
