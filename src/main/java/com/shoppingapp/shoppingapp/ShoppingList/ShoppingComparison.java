package com.shoppingapp.shoppingapp.ShoppingList;

import com.shoppingapp.shoppingapp.model.*;
import com.shoppingapp.shoppingapp.repository.*;
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
    private StoreProductRepository storeProductRepository;
    private StoreRepository storeRepository;
    private StoreProductPriceRepository storeProductPriceRepository;
    private PriceRepository priceRepository;

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
            try {
                if (candidate_items.size() <= 0) {
                    // no alternative
                    // put "no alternative
                    setNoAlternative(product);
                } else {
                    System.out.println(candidate_items.get(0));
                    // add the store price to the list of candidate_items
                    addStorePrice(candidate_items);

                    Collections.sort(candidate_items, (Comparator.comparingDouble(Product::getValue)));

                    setNoAlternative(product);
                    // add check if the price is lower
                    product.setChosen_price(candidate_items.get(0).getValue());
                    storeRepository.findById(candidate_items.get(0).getStore_id()).ifPresent(data -> {
                        product.setChosen_store(data);
                    });
                }
            } catch (Exception e) {
                setNoAlternative(product);
            }
        });
        return shopping_info;
    }

    private Shopping_Info compareItemsWithPrice(Shopping_Info shopping_info) {
        // iterate through the shopping_info's shopping_list ArrayList<Product> and find the cheapest
        shopping_info.getShoppingList().forEach(product -> {
            List<Product> candidate_items = productRepository.findAllProductsWithName(product.getItem_name());
            try {
                if (candidate_items.size() <= 0) {
                    // no alternative
                    // put "no alternative
                    setNoAlternative(product);
                } else {
                    System.out.println(candidate_items.get(0));
                    // add the store price to the list of candidate_items
                    addStorePrice(candidate_items);

                    Collections.sort(candidate_items, (Comparator.comparingDouble(Product::getValue)));

                    setNoAlternative(product);
                    // add check if the price is lower
                    if (product.getValue() > candidate_items.get(0).getValue()) {
                        product.setChosen_price(candidate_items.get(0).getValue());
                        storeRepository.findById(candidate_items.get(0).getStore_id()).ifPresent(data -> {
                            product.setChosen_store(data);
                        });
                    }
                }
            } catch (Exception e) {
                setNoAlternative(product);
            }
        });
        return shopping_info;
    }

    private void setNoAlternative(Product product) {
        product.setChosen_store(null);
        product.setChosen_price(0.0);
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
                storeRepository.findById(storeId).ifPresent(data -> {
                    product.setStore_id(storeId);
                });

                // get from the Store_Product_Price table
                Store_Product_Price price = storeProductPriceRepository.getStoreProductPrice(
                        product.getProduct_id(),
                        storeId
                ).get(0);

                priceRepository.findById(price.getPrice_id()).ifPresent(p -> {
                    product.setValue(p.getValue());
                });
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
