package com.company.networkmovers.modules.maps.repository;

import com.company.networkmovers.modules.maps.entity.MapsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapsRepository extends JpaRepository<MapsEntity, Long> {
}
