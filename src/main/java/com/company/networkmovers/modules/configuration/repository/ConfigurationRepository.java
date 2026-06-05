package com.company.networkmovers.modules.configuration.repository;

import java.util.UUID;

import com.company.networkmovers.modules.configuration.entity.ConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends JpaRepository<ConfigurationEntity, UUID> {
}
