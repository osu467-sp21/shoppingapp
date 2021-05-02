package com.shoppingapp.shoppingapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@Table(name="Store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long store_id;
    String store_name;
    String addr_line_1;
    String addr_line_2;
    String addr_line_3;
    String city;
    String state;
    Integer zip_code;
    String country;

    public Store() {

    }
}
