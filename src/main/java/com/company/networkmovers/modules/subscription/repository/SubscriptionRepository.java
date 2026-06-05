package com.company.networkmovers.modules.subscription.repository;

import java.util.UUID;

import com.company.networkmovers.modules.subscription.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, UUID> {
}
