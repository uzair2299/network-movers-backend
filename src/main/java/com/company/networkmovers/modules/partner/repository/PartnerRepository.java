package com.company.networkmovers.modules.partner.repository;

import java.util.UUID;

import com.company.networkmovers.modules.partner.entity.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<PartnerEntity, UUID> {
}
