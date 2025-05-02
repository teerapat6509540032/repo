package com.example.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CustomerClientRunner implements CommandLineRunner {

    private final MenuClient menuClient;

    public CustomerClientRunner(MenuClient menuClient) {
        this.menuClient = menuClient;
    }

    @Override
    public void run(String... args) {
        String restaurantServiceUrl = "http://localhost:8080";
        menuClient.interactWithRestaurant(restaurantServiceUrl);
    }
}