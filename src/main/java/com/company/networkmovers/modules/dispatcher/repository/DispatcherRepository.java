package com.company.networkmovers.modules.dispatcher.repository;

import com.company.networkmovers.modules.dispatcher.entity.DispatcherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispatcherRepository extends JpaRepository<DispatcherEntity, Long> {
}
