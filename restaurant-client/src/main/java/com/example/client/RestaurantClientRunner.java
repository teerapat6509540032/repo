package com.example.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RestaurantClientRunner implements CommandLineRunner {

    private final ReviewClient reviewClient;

    public RestaurantClientRunner(ReviewClient reviewClient) {
        this.reviewClient = reviewClient;
    }

    @Override
    public void run(String... args) {
        String customerServiceUrl = "http://localhost:8081";
        reviewClient.interactWithCustomer(customerServiceUrl);
    }
}