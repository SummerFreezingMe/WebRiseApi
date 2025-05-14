package com.example.webriseapi.mapper;

import com.example.webriseapi.model.Subscription;
import com.example.webriseapi.model.dto.SubscriptionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    SubscriptionDTO subscriptionToSubscriptionDTO(Subscription subscription);
    Subscription subscriptionDTOToSubscription(SubscriptionDTO subscriptionDTO);
}