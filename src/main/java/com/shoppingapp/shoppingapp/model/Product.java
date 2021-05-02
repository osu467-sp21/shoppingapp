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
    Integer manufacturer_id;
    Integer item_number;
    Integer check_digit;
    Integer size;
    String item_name;
    String size_unit;
    Integer generic_product; //(foreign key)

    // adding price for mvp
    // adding store for mvp
    @Transient
    Double value = 0.0;
    @Transient
    Long store_id;
    @Transient
    Long user_id;
    @Transient
    String date_entered;
    @Transient
    Boolean is_sale;

    public Product() {}
}
