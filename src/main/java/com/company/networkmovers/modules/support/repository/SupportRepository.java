package com.company.networkmovers.modules.support.repository;

import java.util.UUID;

import com.company.networkmovers.modules.support.entity.SupportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportRepository extends JpaRepository<SupportEntity, UUID> {
}
