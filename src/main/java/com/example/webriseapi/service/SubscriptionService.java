package com.example.webriseapi.service;

import com.example.webriseapi.model.dto.SubscriptionDTO;

import java.util.List;

public interface SubscriptionService {
    SubscriptionDTO addSubscription(Long userId, SubscriptionDTO subscriptionDTO);
    List<SubscriptionDTO> getSubscriptions(Long userId);
    void deleteSubscription(Long userId, Long subId);
    List<SubscriptionDTO> getTopSubscriptions();
}