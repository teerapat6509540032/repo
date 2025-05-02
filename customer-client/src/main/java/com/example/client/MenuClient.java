package com.example.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class MenuClient {

    private final RestTemplate restTemplate;

    public MenuClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void interactWithRestaurant(String baseUrl) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please select a service: (Request Menu / Place Order / Exit)");
            String input = scanner.nextLine();

            if ("Exit".equalsIgnoreCase(input)) {
                System.out.println("Service closed");
                break;
            }

            if ("Request Menu".equalsIgnoreCase(input)) {
                try {
                    ResponseEntity<List> response = restTemplate.getForEntity(baseUrl + "/menu", List.class);
                    System.out.println("Menu:");
                    response.getBody().forEach(System.out::println);
                } catch (Exception e) {
                    System.err.println("Failed to get menu: " + e.getMessage());
                }
            } else if ("Place Order".equalsIgnoreCase(input)) {
                System.out.println("Please enter the menu ID you want to order:");
                int menuId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                Map<String, Object> order = Map.of("item", menuId, "quantity", 1);

                try {
                    ResponseEntity<String> response = restTemplate.postForEntity(baseUrl + "/order", order, String.class);
                    System.out.println(response.getBody());
                } catch (Exception e) {
                    System.err.println("Order failed: " + e.getMessage());
                }
            } else {
                System.out.println("Invalid command");
            }
        }
    }
}