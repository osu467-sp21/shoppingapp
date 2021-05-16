package com.shoppingapp.shoppingapp.ShoppingList;

import com.shoppingapp.shoppingapp.model.Product;
import com.shoppingapp.shoppingapp.model.Shopping_Info;
import com.shoppingapp.shoppingapp.model.Shopping_List;
import com.shoppingapp.shoppingapp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class ShoppingComparison {
    // impl for comparison algorithm
    private ShoppingInfoExtractor shoppingInfoExtractor;
//    private EligibleZips eligibleZips;
    private ProductRepository productRepository;

    public Shopping_Info getChosenList(String payload) {
        Shopping_Info shopping_info = compareItems(shoppingInfoExtractor.processPayload(payload));
        return shopping_info;
    }

//    private Shopping_Info compareItems(Shopping_Info shopping_info) {
//        // go through each item of the shoppingList, get the name
//        ArrayList<Map<String, Map<String, Double>>> list = shopping_info.getShoppingList();
//        // top level is "item_name" and then add the "chosenProduct" object
//
//        ArrayList<Product> shoppingItems = shoppingInfoExtractor.processItems(shopping_info.getShoppingList());
//        // for the query could either use the eligibleZips before or after the comparison query
//        AtomicInteger index = new AtomicInteger(0);
//        shoppingItems.forEach(product -> {
//            Map<String, Double> createdItem = new HashMap<>();
//            List<Product> candidate_items = productRepository.findAllProductsWithName(product.getItem_name());
//            if (candidate_items.size() <= 0) {
//                // no alternative
//                // put "no alternative
//                createdItem.put("no cheaper alternative", 0.0);
//            } else{
//                System.out.println(candidate_items.get(0));
//                Collections.sort(candidate_items, (Comparator.comparingDouble(Product::getValue)));
//                createdItem.put("price", candidate_items.get(0).getValue());
//            }
//            list.get(index.intValue()).put("chosenProduct", createdItem);
//            index.addAndGet(1);
//        });
//        // until the eligibleZips are in here, then will do it after
//        return shopping_info;
//    }

    private Shopping_Info compareItems(Shopping_Info shopping_info) {
        // iterate through the shopping_info's shopping_list ArrayList<Product> and find the cheapest
        shopping_info.getShoppingList().forEach(product -> {
            List<Product> candidate_items = productRepository.findAllProductsWithName(product.getItem_name());
            if (candidate_items.size() <= 0) {
                // no alternative
                // put "no alternative
                product.setChosen_store("no cheaper alternative");
                product.setChosen_price(0.0);
            } else{
                System.out.println(candidate_items.get(0));
                Collections.sort(candidate_items, (Comparator.comparingDouble(Product::getValue)));
                product.setChosen_price(candidate_items.get(0).getValue());
                // TODO need to set the get and set the name of the store
            }
        });
        return shopping_info;
    }

}
