package com.shoppingapp.shoppingapp.controllers.StoreController;

import com.okta.jwt.JwtVerificationException;
import com.shoppingapp.shoppingapp.model.Store;
import com.shoppingapp.shoppingapp.oktaJwtVerifier.JwtVerifier;
import com.shoppingapp.shoppingapp.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class StoreController {
    private StoreRepository storeRepository;
    private final JwtVerifier jwtVerifier = new JwtVerifier();

    @GetMapping("/stores")
    public ResponseEntity<?> getAllStores() {
        return new ResponseEntity<>(storeRepository.findAllStores(), HttpStatus.OK);
    }

    @GetMapping("/stores/{store_name}")
    public ResponseEntity<?> getStoreFromName(@PathVariable("store_name") String store_name) {
        return new ResponseEntity<>(storeRepository.getStoreFromName(store_name), HttpStatus.OK);
    }

    @PostMapping("/stores")
    public ResponseEntity<?> addStore(@RequestBody Store store,
                                      @RequestHeader("Authorization") String authorization) {
        try {
            authorization = authorization.replace("Bearer ", "");
            jwtVerifier.accessTokenVerifier.decode(authorization);
            Store newStore = storeRepository.save(store);
            return new ResponseEntity<>(newStore.getStore_id() , HttpStatus.OK);
        }
        catch (JwtVerificationException exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>("invalid access_token", HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("could not add item", HttpStatus.FORBIDDEN);
        }
    }

}
