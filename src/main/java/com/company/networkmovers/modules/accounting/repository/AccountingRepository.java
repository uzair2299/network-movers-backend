package com.company.networkmovers.modules.accounting.repository;

import com.company.networkmovers.modules.accounting.entity.AccountingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountingRepository extends JpaRepository<AccountingEntity, Long> {
}
