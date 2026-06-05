package com.company.networkmovers.modules.complaint.repository;

import java.util.UUID;

import com.company.networkmovers.modules.complaint.entity.ComplaintEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<ComplaintEntity, UUID> {
}
