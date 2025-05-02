package com.example.restaurant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MenuController {

    private final List<MenuItem> menu = List.of(
        new MenuItem(1, "Fried Rice", 50),
        new MenuItem(2, "Pad Thai", 60),
        new MenuItem(3, "Tom Yum Goong", 80)
    );

    @GetMapping("/menu")
    public List<MenuItem> getMenu() {
        return menu;
    }

    @PostMapping("/order")
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest order) {
        try {
            // Find menu by id
            Optional<MenuItem> menuItem = menu.stream()
                .filter(item -> item.getId() == order.getItem())
                .findFirst();

            if (menuItem.isPresent()) {
                return ResponseEntity.ok("Order confirmed: " + menuItem.get().getName());
            } else {
                return ResponseEntity.badRequest().body("Invalid menu ID: " + order.getItem());
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid menu ID format: " + order.getItem());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred: " + e.getMessage());
        }
    }
}