package com.shoppingapp.shoppingapp.repository;

import com.shoppingapp.shoppingapp.model.Store;
import com.shoppingapp.shoppingapp.model.Store_Product;
import com.shoppingapp.shoppingapp.model.Store_Product_Price;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreProductPriceRepository extends CrudRepository<Store_Product_Price, Long> {
}
