package com.shoppingapp.shoppingapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Builder
@Entity
@AllArgsConstructor
@Table(name="Store_Product")
@IdClass(CompositeStoreProduct.class)
public class Store_Product {
    @Id
    Long store_id; // foreign key
    @Id
    Long product_id; // foreign key

    public Store_Product() {
    }

}

