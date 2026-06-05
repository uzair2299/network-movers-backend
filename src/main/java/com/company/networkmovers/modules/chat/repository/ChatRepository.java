package com.company.networkmovers.modules.chat.repository;

import java.util.UUID;

import com.company.networkmovers.modules.chat.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, UUID> {
}
