package com.company.networkmovers.modules.truck.repository;

import com.company.networkmovers.modules.truck.entity.TruckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends JpaRepository<TruckEntity, Long> {
}
