package com.company.networkmovers.modules.ai.repository;

import com.company.networkmovers.modules.ai.entity.AiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AiRepository extends JpaRepository<AiEntity, Long> {
}
