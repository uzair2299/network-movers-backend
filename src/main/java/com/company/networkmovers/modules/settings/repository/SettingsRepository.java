package com.company.networkmovers.modules.settings.repository;

import java.util.UUID;

import com.company.networkmovers.modules.settings.entity.SettingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepository extends JpaRepository<SettingsEntity, UUID> {
}
