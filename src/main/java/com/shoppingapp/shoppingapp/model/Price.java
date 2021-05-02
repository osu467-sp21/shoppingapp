package com.shoppingapp.shoppingapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity
@AllArgsConstructor
@Table(name="Price")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long price_id;

    Double value;
    Long user_id; // foreign key

    Boolean is_sale;
    String date_entered;
    String sale_expiration;

    public Price() {

    }
}
