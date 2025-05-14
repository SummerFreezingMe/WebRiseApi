package com.example.webriseapi.repository;

import com.example.webriseapi.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserId(Long userId);

    @NativeQuery("""
            SELECT
                s.service_name,
                s.description,
                COUNT(s.id) AS subscribers_count
            FROM
                subscriptions s
            GROUP BY
                s.service_name, s.description
            ORDER BY
                subscribers_count DESC
            LIMIT 3""")
    List<Subscription> findTop3ByOrderBySubscribersCountDesc();
}