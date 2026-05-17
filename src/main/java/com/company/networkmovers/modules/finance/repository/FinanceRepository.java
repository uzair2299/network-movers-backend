package com.company.networkmovers.modules.finance.repository;

import com.company.networkmovers.modules.finance.entity.FinanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceRepository extends JpaRepository<FinanceEntity, Long> {
}
