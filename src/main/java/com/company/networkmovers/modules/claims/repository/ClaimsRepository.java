package com.company.networkmovers.modules.claims.repository;

import com.company.networkmovers.modules.claims.entity.ClaimsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimsRepository extends JpaRepository<ClaimsEntity, Long> {
}
