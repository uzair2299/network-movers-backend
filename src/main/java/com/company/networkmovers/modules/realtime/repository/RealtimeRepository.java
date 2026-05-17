package com.company.networkmovers.modules.realtime.repository;

import com.company.networkmovers.modules.realtime.entity.RealtimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealtimeRepository extends JpaRepository<RealtimeEntity, Long> {
}
