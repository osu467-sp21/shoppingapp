package com.shoppingapp.shoppingapp.controllers.ShoppingListController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shoppingapp.shoppingapp.ShoppingList.ShoppingComparison;
import com.shoppingapp.shoppingapp.ShoppingList.ShoppingInfoExtractor;
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

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class ShoppingListController {

    private ShoppingComparison shoppingListComparison;
    private ProductRepository productRepository;
    private StoreRepository storeRepository;
    private StoreProductRepository storeProductRepository;
    private PriceRepository priceRepository;
    private StoreProductPriceRepository storeProductPriceRepository;
    private UserRepository userRepository;

    private final JwtVerifier jwtVerifier = new JwtVerifier();

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

    @GetMapping(value = "/shoppingList/{user_id}")
    ResponseEntity<?> getShoppingListWithUserId(@PathVariable(value = "user_id") String user_id,
                                               @RequestHeader("Authorization") String authorization) {
        try {
            authorization = jwtVerifier.stripBearer(authorization);
            Jwt jwt = jwtVerifier.accessTokenVerifier.decode(authorization);
            String jwt_uid = jwt.getClaims().get("uid").toString();
            if (user_id.equals(jwt_uid)) {
//                Select shopping list items here
//                ArrayList<Product> list =
                return new ResponseEntity<>("", HttpStatus.OK);

            }
            return new ResponseEntity<>("access_token does not match user_id", HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value= "/products")
    ResponseEntity<?> getAllProducts() {
        // go through each product and have to retrieve the store and the price
        List<Product> allProducts = productRepository.findAllProducts();
        addStorePrice(allProducts);
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @GetMapping(value= "/products/{name}")
    ResponseEntity<?> getAllWithName(@PathVariable(value="name") String name) {
        List<Product> allProducts = productRepository.findAllProductsWithName(name);
        addStorePrice(allProducts);
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @GetMapping(value= "/products/partial/pre/{name}")
    ResponseEntity<?> getAllWithPartialName(@PathVariable(value="name") String name) {
        List<Product> allProducts = productRepository.findAllProductsWithName(name);
        addStorePrice(allProducts);
        return new ResponseEntity<>(productRepository.findAllProductsWithPartialName(name), HttpStatus.OK);
    }

    @GetMapping(value= "/products/partial/complete/{name}")
    ResponseEntity<?> findAllProductsContainingName(@PathVariable(value="name") String name) {
        List<Product> allProducts = productRepository.findAllProductsContainingName(name);
        addStorePrice(allProducts);
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    protected void addStorePrice(List<Product> allProducts) {
        int i;
        for (i = 0; i < allProducts.size(); ++i) {
            // get from the Store_Product table
            try {
                Product product = allProducts.get(i);
                List<Store_Product> retrievedStoreProduct = storeProductRepository.findStoreProduct(product.getProduct_id());
                Long storeId = retrievedStoreProduct.get(0).getStore_id();
                System.out.println(storeId);

                // get the store info, Store
                storeRepository.findById(storeId).ifPresent(data -> { product.setStore_id(storeId); });

                // get from the Store_Product_Price table
                Store_Product_Price price = storeProductPriceRepository.getStoreProductPrice(
                        product.getProduct_id(),
                        storeId
                ).get(0);

                priceRepository.findById(price.getPrice_id()).ifPresent(p -> {product.setValue(p.getValue());});
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @GetMapping(value= "/products/barcode/{barcode}")
    ResponseEntity<?> findProductWithBarcode(@PathVariable(value="barcode") String barcode) {
        return new ResponseEntity<>(productRepository.findProductWithBarcode(barcode), HttpStatus.OK);
    }

    @PostMapping(value= "/products")
    ResponseEntity<?> addProduct(@RequestBody Product product,
                                  @RequestHeader("Authorization") String authorization) {
        String user_id;
        try {
            authorization = authorization.replace("Bearer ", "");
            Jwt jwt = jwtVerifier.accessTokenVerifier.decode(authorization);
            user_id = jwt.getClaims().get("uid").toString();
        }
        catch (JwtVerificationException exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>("invalid access_token", HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // check that the store exists
        storeRepository.findById(product.getStore_id());
        System.out.println(storeRepository);

        Product savedProduct;
        try {
            savedProduct = productRepository.save(product);
        }
        catch (Exception ignored) {
            List<Product> foundProducts = productRepository.findProductWithBarcode(product.getBarcode());
            savedProduct = foundProducts.get(0);
        }

        // add into the Store_Product table
        try {
            storeProductRepository.save(new Store_Product(product.getStore_id(),
                    savedProduct.getProduct_id()));
        }
        catch (Exception exception) {
            System.out.println(exception);
        } // May throw Duplicate Exception

        // add into the Price table
        Price price = Price.builder().user_id(user_id)
                .value(product.getValue()).date_entered(product.getDate_entered())
                .is_sale(product.getIs_sale()).build();
        Price savedPrice = priceRepository.save(price);

        // add into the Store_Product_Price table
        storeProductPriceRepository.save(Store_Product_Price.builder()
                .price_id(savedPrice.getPrice_id())
                .store_id(product.getStore_id())
                .product_id(savedProduct.getProduct_id()).build());

        // Increment master_shopper_level
        userRepository.incrementMSL(user_id);
        return new ResponseEntity<>(savedProduct, HttpStatus.OK);
    }



}
