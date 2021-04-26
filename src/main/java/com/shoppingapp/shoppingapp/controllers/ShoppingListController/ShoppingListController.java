package com.shoppingapp.shoppingapp.controllers.ShoppingListController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shoppingapp.shoppingapp.ShoppingList.ShoppingComparison;
import com.shoppingapp.shoppingapp.ShoppingList.ShoppingInfoExtractor;
import com.shoppingapp.shoppingapp.model.Product;
import com.shoppingapp.shoppingapp.model.Shopping_Info;
import com.shoppingapp.shoppingapp.repository.ProductRepository;
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
    private ProductRepository productRepository;

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
             shoppingListComparison.getChosenList(payload);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        }
        catch (IllegalFieldValueException illegalFieldValueException) {
            System.out.println(illegalFieldValueException);
            return new ResponseEntity<>(illegalFieldValueException, HttpStatus.BAD_REQUEST);
        }
        catch (Exception exception) {
            System.out.println(exception);
            return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value= "/products")
    ResponseEntity<?> getAllProducts() {
        return new ResponseEntity<>(productRepository.findAllProducts(), HttpStatus.OK);
    }

    @GetMapping(value= "/products/{name}")
    ResponseEntity<?> getAllWithName(@PathVariable(value="name") String name) {
        return new ResponseEntity<>(productRepository.findAllProductsWithName(name), HttpStatus.OK);
    }

    @PostMapping(value= "/products")
    ResponseEntity<?> saveProductWithName(@RequestBody Product product) {
        productRepository.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
