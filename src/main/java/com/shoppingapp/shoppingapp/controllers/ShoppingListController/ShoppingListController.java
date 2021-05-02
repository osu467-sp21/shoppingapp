package com.shoppingapp.shoppingapp.controllers.ShoppingListController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shoppingapp.shoppingapp.ShoppingList.ShoppingComparison;
import com.shoppingapp.shoppingapp.ShoppingList.ShoppingInfoExtractor;
import com.shoppingapp.shoppingapp.model.Price;
import com.shoppingapp.shoppingapp.model.Product;
import com.shoppingapp.shoppingapp.model.Shopping_Info;
import com.shoppingapp.shoppingapp.model.Store_Product;
import com.shoppingapp.shoppingapp.repository.PriceRepository;
import com.shoppingapp.shoppingapp.repository.ProductRepository;
import com.shoppingapp.shoppingapp.repository.StoreProductRepository;
import com.shoppingapp.shoppingapp.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.joda.time.IllegalFieldValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@AllArgsConstructor
public class ShoppingListController {

    private ShoppingComparison shoppingListComparison;
    private ProductRepository productRepository;
    private StoreRepository storeRepository;
    private StoreProductRepository storeProductRepository;
    private PriceRepository priceRepository;

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
            Shopping_Info chosenList = shoppingListComparison.getChosenList(payload);
            return new ResponseEntity<>(chosenList, HttpStatus.OK);
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
    ResponseEntity<?> saveProduct(@RequestBody Product product) {
        // check that the store exists
        storeRepository.findById(product.getStore_id());
        System.out.println(storeRepository);

        Product savedProduct = productRepository.save(product);

        // add into the Store_Product table
        storeProductRepository.save(new Store_Product(product.getStore_id(),
                savedProduct.getProduct_id()));

        // add into the Price table
        Price price = Price.builder().user_id(product.getUser_id())
                .value(product.getValue()).date_entered(product.getDate_entered())
                .is_sale(product.getIs_sale()).build();
        priceRepository.save(price);

        System.out.println(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
