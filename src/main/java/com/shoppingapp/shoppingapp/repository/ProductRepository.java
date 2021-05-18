package com.shoppingapp.shoppingapp.repository;

import com.shoppingapp.shoppingapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

//    @Query(value = "SELECT P FROM PRODUCT P WHERE P.item_name = ?1")
//    List<Product> getAllProductsName(String name);
    @Query(value = "SELECT P FROM Product P")
    List<Product> findAllProducts();

    @Query(value = "SELECT P FROM Product P WHERE P.item_name = ?1")
    List<Product> findAllProductsWithName(String item_name);

    @Query(value = "SELECT P FROM Product P WHERE P.item_name LIKE :item_name%")
    List<Product> findAllProductsWithPartialName(@Param("item_name") String item_name);

    @Query(value = "SELECT P FROM Product P WHERE P.item_name LIKE %:item_name%")
    List<Product> findAllProductsContainingName(@Param("item_name") String item_name);

    @Query(value = "SELECT P FROM Product P WHERE P.barcode = ?1")
    Product findByBarcode(String barcode);
}
