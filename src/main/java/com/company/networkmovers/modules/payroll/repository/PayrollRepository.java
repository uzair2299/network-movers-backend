package com.company.networkmovers.modules.payroll.repository;

import com.company.networkmovers.modules.payroll.entity.PayrollEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayrollRepository extends JpaRepository<PayrollEntity, Long> {
}
