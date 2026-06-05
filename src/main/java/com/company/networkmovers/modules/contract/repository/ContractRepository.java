package com.company.networkmovers.modules.contract.repository;

import java.util.UUID;

import com.company.networkmovers.modules.contract.entity.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity, UUID> {
}
