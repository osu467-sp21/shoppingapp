package com.shoppingapp.shoppingapp.ShoppingList;

import com.shoppingapp.shoppingapp.model.Product;
import com.shoppingapp.shoppingapp.model.Shopping_Info;
import com.shoppingapp.shoppingapp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ShoppingComparison {
    // impl for comparison algorithm
    private ShoppingInfoExtractor shoppingInfoExtractor;
    private EligibleZips eligibleZips;
    private ProductRepository productRepository;

    public String getChosenList(String payload) {
        Shopping_Info shopping_info = compareItems(shoppingInfoExtractor.processPayload(payload));
        return shopping_info.toString();
    }

    private Shopping_Info compareItems(Shopping_Info shopping_info) {
        // go through each item of the shoppingList, get the name
        Map<String, Float> shopping_items = shopping_info.getShoppingList();
        shopping_items.forEach((String item, Float price) -> {
            // query to get the Item based on the name
            System.out.println(item);
            System.out.println(price);
            List<Product> candidate_items = productRepository.findAllProductsWithName(item);
        });
        // for the query could either use the eligibleZips before or after the comparison query
        // until the eligibleZips are in here, then will do it after
        return shopping_info;
    }

}
