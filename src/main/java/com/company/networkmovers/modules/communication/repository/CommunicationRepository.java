package com.company.networkmovers.modules.communication.repository;

import com.company.networkmovers.modules.communication.entity.CommunicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunicationRepository extends JpaRepository<CommunicationEntity, Long> {
}
