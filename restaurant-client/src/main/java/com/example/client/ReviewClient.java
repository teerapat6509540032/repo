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

    public void requestReview(String baseUrl) {
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(baseUrl + "/review", Map.class);
            System.out.println("Received review: " + response.getBody());
        } catch (Exception e) {
            System.err.println("Failed to get review: " + e.getMessage());
        }
    }

    public void sendMenuForFeedback(String baseUrl) {
        Map<String, String> menu = Map.of(
            "name", "ต้มยำกุ้ง",
            "description", "เมนูใหม่ที่ต้องการทดสอบ"
        );

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(baseUrl + "/test-menu", menu, Map.class);
            System.out.println("Received feedback: " + response.getBody());
        } catch (Exception e) {
            System.err.println("Failed to send menu for feedback: " + e.getMessage());
        }
    }

    public void interactWithCustomer(String baseUrl) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("กรุณาเลือกบริการ: (ขอรีวิว / ขอเทสเมนู / ออก)");
            String input = scanner.nextLine();

            if ("ออก".equals(input)) {
                System.out.println("ปิดบริการ");
                break;
            }

            if ("ขอรีวิว".equals(input)) {
                try {
                    ResponseEntity<Map> response = restTemplate.getForEntity(baseUrl + "/review", Map.class);
                    System.out.println("รีวิวจากลูกค้า: " + response.getBody());
                } catch (Exception e) {
                    System.err.println("Failed to get review: " + e.getMessage());
                }
            } else if ("ขอเทสเมนู".equals(input)) {
                System.out.println("ต้องการเทสเมนูอะไรครับ:");
                String menuName = scanner.nextLine();
                Map<String, String> menu = Map.of("name", menuName);

                try {
                    ResponseEntity<Map> response = restTemplate.postForEntity(baseUrl + "/test-menu", menu, Map.class);
                    System.out.println("Feedback: " + response.getBody());
                } catch (Exception e) {
                    System.err.println("Failed to test menu: " + e.getMessage());
                }
            } else {
                System.out.println("คำสั่งไม่ถูกต้อง");
            }
        }
    }
}