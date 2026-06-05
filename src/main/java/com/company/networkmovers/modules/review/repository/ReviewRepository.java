package com.company.networkmovers.modules.review.repository;

import java.util.UUID;

import com.company.networkmovers.modules.review.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, UUID> {
}
