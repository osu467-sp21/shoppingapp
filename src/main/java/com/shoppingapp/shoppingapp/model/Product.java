package com.shoppingapp.shoppingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity
@AllArgsConstructor
@Table(name="Product")
public class Product {
    // UUID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long product_id;
    String barcode;
    Integer size;
    String item_name;
    String size_unit;
//    Integer generic_product; //(foreign key)

    // adding price for mvp
    // adding store for mvp
    @Transient
    Double value = 0.0;
    @Transient
    Long store_id;
    @Transient
    String user_id;
    @Transient
    String date_entered;
    @Transient
    Boolean is_sale;
    @Transient
    Store chosen_store;
    @Transient
    Double chosen_price;
    @Transient
    String chosen_unit;

    public Product() {}
}
