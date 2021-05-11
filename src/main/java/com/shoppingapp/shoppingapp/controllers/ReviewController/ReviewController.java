package com.shoppingapp.shoppingapp.controllers.ReviewController;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ReviewController {

    @GetMapping("/store/{store_id}/reviews/{review_id}/users/{user_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getReviewForAStore(@PathVariable(value = "store_id") String store_id,
                                             @PathVariable(value = "review_id") String review_id,
                                                @PathVariable(value = "user_id") String user_id){
        return new ResponseEntity("retrieved value", HttpStatus.OK);
    }

    @PostMapping("/store/{store_id}/reviews/{review_id}/users/{user_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addReviewForAStore(@PathVariable(value = "store_id") String store_id,
                                                @PathVariable(value = "review_id") String review_id,
                                                @PathVariable(value = "user_id") String user_id){
        return new ResponseEntity("added value", HttpStatus.OK);
    }
}
