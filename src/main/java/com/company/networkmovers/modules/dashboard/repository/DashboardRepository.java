package com.company.networkmovers.modules.dashboard.repository;

import java.util.UUID;

import com.company.networkmovers.modules.dashboard.entity.DashboardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardRepository extends JpaRepository<DashboardEntity, UUID> {
}
