package com.example.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Scanner;

@Component
public class ReviewClient {

    private final RestTemplate restTemplate;

    public ReviewClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void interactWithCustomer(String baseUrl) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please select a service: (Request Review / Test Menu / Exit)");
            String input = scanner.nextLine();

            if ("Exit".equalsIgnoreCase(input)) {
                System.out.println("Service closed");
                break;
            }

            if ("Request Review".equalsIgnoreCase(input)) {
                try {
                    ResponseEntity<Map> response = restTemplate.getForEntity(baseUrl + "/review", Map.class);
                    System.out.println("Customer review: " + response.getBody());
                } catch (Exception e) {
                    System.err.println("Failed to get review: " + e.getMessage());
                }
            } else if ("Test Menu".equalsIgnoreCase(input)) {
                System.out.println("What menu would you like to test?");
                String menuName = scanner.nextLine();
                Map<String, String> menu = Map.of("name", menuName);

                try {
                    ResponseEntity<Map> response = restTemplate.postForEntity(baseUrl + "/test-menu", menu, Map.class);
                    System.out.println("Feedback: " + response.getBody());
                } catch (Exception e) {
                    System.err.println("Failed to test menu: " + e.getMessage());
                }
            } else {
                System.out.println("Invalid command");
            }
        }
    }
}