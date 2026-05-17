package com.company.networkmovers.modules.logistics.repository;

import com.company.networkmovers.modules.logistics.entity.LogisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticsRepository extends JpaRepository<LogisticsEntity, Long> {
}
