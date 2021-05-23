package com.shoppingapp.shoppingapp.controllers.ShoppingListController;

import com.shoppingapp.shoppingapp.ShoppingList.ShoppingComparison;
import com.shoppingapp.shoppingapp.model.*;
import com.shoppingapp.shoppingapp.repository.*;
import com.shoppingapp.shoppingapp.oktaJwtVerifier.JwtVerifier;
import lombok.AllArgsConstructor;
import org.joda.time.IllegalFieldValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.okta.jwt.Jwt;
import com.okta.jwt.JwtVerificationException;

@RestController
@AllArgsConstructor
public class ShoppingListController {

    private ShoppingComparison shoppingListComparison;
    private ProductRepository productRepository;
    private StoreRepository storeRepository;
    private StoreProductRepository storeProductRepository;
    private PriceRepository priceRepository;
    private StoreProductPriceRepository storeProductPriceRepository;

    private static JwtVerifier jwtVerifier;

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

    @GetMapping(value= "/products/partial/pre/{name}")
    ResponseEntity<?> getAllWithPartialName(@PathVariable(value="name") String name) {
        return new ResponseEntity<>(productRepository.findAllProductsWithPartialName(name), HttpStatus.OK);
    }

    @GetMapping(value= "/products/partial/complete/{name}")
    ResponseEntity<?> findAllProductsContainingName(@PathVariable(value="name") String name) {
        return new ResponseEntity<>(productRepository.findAllProductsContainingName(name), HttpStatus.OK);
    }

    @GetMapping(value= "/products/barcode/{barcode}")
    ResponseEntity<?> findProductWithBarcode(@PathVariable(value="barcode") String barcode) {
        return new ResponseEntity<>(productRepository.findProductWithBarcode(barcode), HttpStatus.OK);
    }

    @PostMapping(value= "/products")
    ResponseEntity<?> saveProduct(@RequestBody Product product,
                                  @RequestHeader("Authorization") String authorization) {
        String user_id;
        try {
            authorization = authorization.replace("Bearer ", "");
            Jwt jwt = jwtVerifier.accessTokenVerifier.decode(authorization);
            user_id = jwt.getClaims().get("sub").toString();
        }
        catch (Exception e) {
            return new ResponseEntity("invalid access_token", HttpStatus.UNAUTHORIZED);
        }
        // check that the store exists
        System.out.println(product.getItem_name());
        storeRepository.findById(product.getStore_id());
        System.out.println(storeRepository);

        Product savedProduct = productRepository.save(product);

        // add into the Store_Product table
        storeProductRepository.save(new Store_Product(product.getStore_id(),
                savedProduct.getProduct_id()));

        // add into the Price table
        Price price = Price.builder().user_id(user_id)
                .value(product.getValue()).date_entered(product.getDate_entered())
                .is_sale(product.getIs_sale()).build();
        Price savedPrice = priceRepository.save(price);

        // add into the Store_Product_Price table
        storeProductPriceRepository.save(Store_Product_Price.builder()
                .price_id(savedPrice.getPrice_id())
                .store_id(product.getStore_id()).product_id(product.getProduct_id()).build());

        System.out.println(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
