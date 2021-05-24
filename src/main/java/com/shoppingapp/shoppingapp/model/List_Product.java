package com.shoppingapp.shoppingapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Entity;

@Data
@Builder
@Entity
@AllArgsConstructor
@Table(name="List_Product")
@IdClass(CompositeListProduct.class)
public class List_Product {
    @Id
    Long shopping_list_id; // foreign key
    @Id
    Long product_id; // foreign key

    public List_Product () {}
}
