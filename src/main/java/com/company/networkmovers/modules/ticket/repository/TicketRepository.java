package com.company.networkmovers.modules.ticket.repository;

import java.util.UUID;

import com.company.networkmovers.modules.ticket.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, UUID> {
}
