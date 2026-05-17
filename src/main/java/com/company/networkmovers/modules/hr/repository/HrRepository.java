package com.company.networkmovers.modules.hr.repository;

import com.company.networkmovers.modules.hr.entity.HrEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HrRepository extends JpaRepository<HrEntity, Long> {
}
