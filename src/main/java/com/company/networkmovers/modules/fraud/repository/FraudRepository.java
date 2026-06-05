package com.company.networkmovers.modules.fraud.repository;

import java.util.UUID;

import com.company.networkmovers.modules.fraud.entity.FraudEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudRepository extends JpaRepository<FraudEntity, UUID> {
}
