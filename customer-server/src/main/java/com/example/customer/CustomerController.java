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
            "comment", "menu tastes good, but pricing should be lower."
        );
        return ResponseEntity.ok(review);
    }

    @PostMapping("/test-menu")
    public ResponseEntity<Map<String, String>> testMenu(@RequestBody Map<String, String> menu) {
        System.out.println("Received menu for testing: " + menu);

        Map<String, String> feedback = Map.of(
            "menu", menu.get("name"),
            "feedback", "The taste is good, but it should be spicier."
        );

        return ResponseEntity.ok(feedback);
    }
}