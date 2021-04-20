package com.shoppingapp.shoppingapp.controllers.ShoppingListController;

import com.shoppingapp.shoppingapp.ShoppingList.ShoppingComparison;
import com.shoppingapp.shoppingapp.ShoppingList.ShoppingInfoExtractor;
import com.shoppingapp.shoppingapp.model.Shopping_Info;
import lombok.AllArgsConstructor;
import org.joda.time.IllegalFieldValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
public class ShoppingListController {

    private ShoppingComparison shoppingListComparison;
    private ShoppingInfoExtractor shoppingInfoExtractor;

    @GetMapping(value = {"/", "/home"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String home() {
        return "home";
    }

    // request should contain a JSON object representing the shopping list
    @PostMapping(value = "/shoppingList")
    public ResponseEntity<?> compareShoppingList(@RequestBody(required=true) String payload) {
        // handler for querying, extracting, and comparing the items
        try {
            // Shopping_Info shopping_info = shoppingInfoExtractor.processPayload(payload);
            // TODO: call shoppingListComparison.getChosenList(shopping_info);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        }
        catch (IllegalFieldValueException illegalFieldValueException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
