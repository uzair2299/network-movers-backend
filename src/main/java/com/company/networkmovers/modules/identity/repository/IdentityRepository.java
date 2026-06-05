package com.company.networkmovers.modules.identity.repository;

import java.util.UUID;

import com.company.networkmovers.modules.identity.entity.IdentityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityRepository extends JpaRepository<IdentityEntity, UUID> {
}
