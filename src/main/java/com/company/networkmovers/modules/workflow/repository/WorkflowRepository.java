package com.company.networkmovers.modules.workflow.repository;

import java.util.UUID;

import com.company.networkmovers.modules.workflow.entity.WorkflowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowRepository extends JpaRepository<WorkflowEntity, UUID> {
}
