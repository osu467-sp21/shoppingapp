package com.shoppingapp.shoppingapp.repository;

import com.shoppingapp.shoppingapp.model.List_Product;
import com.shoppingapp.shoppingapp.model.Product;
import com.shoppingapp.shoppingapp.model.Shopping_List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ListProductRepository extends CrudRepository<List_Product, Long> {

    @Query(value = "SELECT LP FROM List_Product LP WHERE LP.shopping_list_id = ?1")
    List<Product> findAllListProduct(Long shopping_list_id );
}