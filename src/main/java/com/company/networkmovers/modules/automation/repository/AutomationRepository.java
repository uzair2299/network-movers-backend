package com.company.networkmovers.modules.automation.repository;

import com.company.networkmovers.modules.automation.entity.AutomationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomationRepository extends JpaRepository<AutomationEntity, Long> {
}
