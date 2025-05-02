package com.example.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CustomerController {

    @GetMapping("/review")
    public ResponseEntity<Map<String, Object>> provideReview() {
        Map<String, Object> review = Map.of(
            "score", 4,
            "comment", "เมนูใหม่รสชาติดี แต่ควรเพิ่มความเผ็ด"
        );
        return ResponseEntity.ok(review);
    }

    @PostMapping("/test-menu")
    public ResponseEntity<Map<String, String>> testMenu(@RequestBody Map<String, String> menu) {
        System.out.println("Received menu for testing: " + menu);

        Map<String, String> feedback = Map.of(
            "menu", menu.get("name"),
            "feedback", "รสชาติดี แต่ควรเพิ่มความเผ็ด"
        );

        return ResponseEntity.ok(feedback);
    }
}