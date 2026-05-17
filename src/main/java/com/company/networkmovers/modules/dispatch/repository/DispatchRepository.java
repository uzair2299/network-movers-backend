package com.company.networkmovers.modules.dispatch.repository;

import com.company.networkmovers.modules.dispatch.entity.DispatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispatchRepository extends JpaRepository<DispatchEntity, Long> {
}
