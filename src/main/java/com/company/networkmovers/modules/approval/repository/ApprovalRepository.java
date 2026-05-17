package com.company.networkmovers.modules.approval.repository;

import com.company.networkmovers.modules.approval.entity.ApprovalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalRepository extends JpaRepository<ApprovalEntity, Long> {
}
