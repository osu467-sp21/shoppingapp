package com.shoppingapp.shoppingapp.repository;

import com.shoppingapp.shoppingapp.model.Store;
import com.shoppingapp.shoppingapp.model.Store_Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreProductRepository extends CrudRepository<Store_Product, Long> {

    @Query(value = "SELECT SP FROM Store_Product SP")
    List<Store> findAllStoresProduct();
}
