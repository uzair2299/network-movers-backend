package com.company.networkmovers.modules.lookup.repository;

import com.company.networkmovers.modules.lookup.entity.LookupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LookupRepository extends JpaRepository<LookupEntity, Long> {
}
