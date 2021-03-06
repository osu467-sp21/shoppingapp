package com.shoppingapp.shoppingapp.repository;

import com.shoppingapp.shoppingapp.model.Price;
import com.shoppingapp.shoppingapp.model.Store;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends CrudRepository<Price, Long> {
}
