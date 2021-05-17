package com.shoppingapp.shoppingapp.repository;

import com.shoppingapp.shoppingapp.model.Product;
import com.shoppingapp.shoppingapp.model.Store;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends CrudRepository<Store, Long> {

    @Query(value = "SELECT S FROM Store S")
    List<Store> findAllStores();

    @Query(value = "SELECT S FROM Store S WHERE S.store_name = :store_name")
    List<Store> getStoreFromName(@Param("store_name")String store_name);
}
