package com.company.networkmovers.modules.taxation.repository;

import com.company.networkmovers.modules.taxation.entity.TaxationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxationRepository extends JpaRepository<TaxationEntity, Long> {
}
