package com.shoppingapp.shoppingapp.repository;

import com.shoppingapp.shoppingapp.model.Store;
import com.shoppingapp.shoppingapp.model.Store_Product;
import com.shoppingapp.shoppingapp.model.Store_Product_Price;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreProductPriceRepository extends CrudRepository<Store_Product_Price, Long> {

    @Query(value = "SELECT SP FROM Store_Product_Price SP WHERE SP.product_id = :productId AND SP.store_id = :storeId")
    List<Store_Product_Price> getStoreProductPrice(@Param("productId") Long productId,
                                                   @Param("storeId") Long storeId);
}
