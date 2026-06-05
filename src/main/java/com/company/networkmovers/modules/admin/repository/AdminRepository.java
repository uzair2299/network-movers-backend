package com.company.networkmovers.modules.admin.repository;

import java.util.UUID;

import com.company.networkmovers.modules.admin.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, UUID> {
}
