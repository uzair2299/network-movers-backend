package com.company.networkmovers.modules.backup.repository;

import com.company.networkmovers.modules.backup.entity.BackupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackupRepository extends JpaRepository<BackupEntity, Long> {
}
