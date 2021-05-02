package com.shoppingapp.shoppingapp.controllers.StoreController;

import com.shoppingapp.shoppingapp.model.Store;
import com.shoppingapp.shoppingapp.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class StoreController {
    private StoreRepository storeRepository;

    @GetMapping("/stores")
    public ResponseEntity<?> getAllStores() {
        return new ResponseEntity<>(storeRepository.findAllStores(), HttpStatus.OK);
    }

    @PostMapping("/stores")
    public ResponseEntity<?> addStore(@RequestBody Store store) {
        return new ResponseEntity<>(storeRepository.save(store), HttpStatus.OK);
    }

}
