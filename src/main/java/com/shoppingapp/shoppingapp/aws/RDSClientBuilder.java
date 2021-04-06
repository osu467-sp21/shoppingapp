package com.shoppingapp.shoppingapp.aws;

import com.amazonaws.services.rds.AmazonRDSClientBuilder;
import lombok.extern.slf4j.Slf4j;

// to be injected into the ShoppingListController
public class RDSClientBuilder {
    AmazonRDSClientBuilder clientBuilder;
}
