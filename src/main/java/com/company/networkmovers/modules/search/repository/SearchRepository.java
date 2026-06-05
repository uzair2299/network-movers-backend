package com.company.networkmovers.modules.search.repository;

import java.util.UUID;

import com.company.networkmovers.modules.search.entity.SearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends JpaRepository<SearchEntity, UUID> {
}
