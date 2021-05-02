package com.shoppingapp.shoppingapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Data
@Builder
@Entity
@AllArgsConstructor
@Table(name="Store_Product_Price")
@IdClass(CompositeStoreProductPrice.class)
public class Store_Product_Price {
    @Id
    Long store_id; // foreign key
    @Id
    Long product_id; // foreign key
    @Id
    Long price_id;

    public Store_Product_Price() {

    }
}


