package com.example.webriseapi.service.impl;

import com.example.webriseapi.controller.UserController;
import com.example.webriseapi.mapper.SubscriptionMapper;
import com.example.webriseapi.model.Subscription;
import com.example.webriseapi.model.User;
import com.example.webriseapi.model.dto.SubscriptionDTO;
import com.example.webriseapi.repository.SubscriptionRepository;
import com.example.webriseapi.repository.UserRepository;
import com.example.webriseapi.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    private final UserRepository userRepository;

    private final SubscriptionMapper subscriptionMapper;

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, UserRepository userRepository, SubscriptionMapper subscriptionMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
        this.subscriptionMapper = subscriptionMapper;
    }

    @Override
    public SubscriptionDTO addSubscription(Long userId, SubscriptionDTO subscriptionDTO) {
        logger.info(MessageFormat.format("Add subscription for User with id: {0}", userId));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Subscription subscription = subscriptionMapper.subscriptionDTOToSubscription(subscriptionDTO);
        subscription.setUser(user);
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return subscriptionMapper.subscriptionToSubscriptionDTO(savedSubscription);
    }

    @Override
    public List<SubscriptionDTO> getSubscriptions(Long userId) {
        logger.info(MessageFormat.format("Get subscriptions for User with id: {0}", userId));
        List<Subscription> subscriptions = subscriptionRepository.findByUserId(userId);
        return subscriptions.stream()
                .map(subscriptionMapper::subscriptionToSubscriptionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSubscription(Long userId, Long subId) {
        logger.info(MessageFormat.format("Delete subscription with id: {0} for User with id: {1}",
                subId, userId));
        Subscription subscription = subscriptionRepository.findById(subId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
        if (!subscription.getUser().getId().equals(userId)) {
            throw new RuntimeException("Subscription does not belong to the user");
        }
        subscriptionRepository.deleteById(subId);
    }

    @Override
    public List<SubscriptionDTO> getTopSubscriptions() {
        logger.info("Get top 3 subscriptions");
        List<Subscription> topSubscriptions = subscriptionRepository.findTop3ByOrderBySubscribersCountDesc();
        return topSubscriptions.stream()
                .map(subscriptionMapper::subscriptionToSubscriptionDTO)
                .collect(Collectors.toList());
    }
}