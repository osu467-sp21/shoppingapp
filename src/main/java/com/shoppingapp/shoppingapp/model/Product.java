package com.shoppingapp.shoppingapp.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@Table(name="Product")
public class Product {
    // UUID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer product_id;
    Integer manufacturer_id;
    Integer item_number;
    Integer check_digit;
    Integer size;
    String item_name;
    String size_unit;
    Integer generic_product; //(foreign key)

    public Product() {

    }
}
