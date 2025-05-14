package com.example.webriseapi.controller;

import com.example.webriseapi.model.dto.SubscriptionDTO;
import com.example.webriseapi.service.SubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public ResponseEntity<SubscriptionDTO> addSubscription(@PathVariable Long userId, @RequestBody SubscriptionDTO subscriptionDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subscriptionService.addSubscription(userId, subscriptionDTO));
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionDTO>> getSubscriptions(@PathVariable Long userId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subscriptionService.getSubscriptions(userId));
    }

    @DeleteMapping("/{subId}")
    public void deleteSubscription(@PathVariable Long userId, @PathVariable Long subId) {
        subscriptionService.deleteSubscription(userId, subId);
        ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted successfully");
    }

    @GetMapping("/top")
    public ResponseEntity<List<SubscriptionDTO>> getTopSubscriptions() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subscriptionService.getTopSubscriptions());
    }
}