package com.company.networkmovers.modules.report.repository;

import java.util.UUID;

import com.company.networkmovers.modules.report.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, UUID> {
}
