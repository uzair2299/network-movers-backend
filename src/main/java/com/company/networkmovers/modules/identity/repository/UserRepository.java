package com.company.networkmovers.modules.identity.repository;

import com.company.networkmovers.modules.identity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.profile WHERE u.username = :username")
    Optional<User> findByUsernameWithProfile(@Param("username") String username);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Query(value = "SELECT u FROM User u LEFT JOIN FETCH u.profile " +
           "WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(u.profile.firstName) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(u.profile.lastName) LIKE LOWER(CONCAT('%', :search, '%'))",
           countQuery = "SELECT count(u) FROM User u LEFT JOIN u.profile " +
           "WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(u.profile.firstName) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(u.profile.lastName) LIKE LOWER(CONCAT('%', :search, '%'))")
    org.springframework.data.domain.Page<User> findBySearch(@Param("search") String search, org.springframework.data.domain.Pageable pageable);

    @Query(value = "SELECT u FROM User u LEFT JOIN FETCH u.profile",
           countQuery = "SELECT count(u) FROM User u")
    org.springframework.data.domain.Page<User> findAllWithProfile(org.springframework.data.domain.Pageable pageable);

    @Query(value = "SELECT u FROM User u LEFT JOIN FETCH u.profile WHERE u.enabled = true",
           countQuery = "SELECT count(u) FROM User u WHERE u.enabled = true")
    org.springframework.data.domain.Page<User> findAllActive(org.springframework.data.domain.Pageable pageable);

    @Query(value = "SELECT u FROM User u LEFT JOIN FETCH u.profile " +
           "WHERE u.enabled = true AND (" +
           "LOWER(u.username) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(u.profile.firstName) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(u.profile.lastName) LIKE LOWER(CONCAT('%', :search, '%')))",
           countQuery = "SELECT count(u) FROM User u LEFT JOIN u.profile " +
           "WHERE u.enabled = true AND (" +
           "LOWER(u.username) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(u.profile.firstName) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(u.profile.lastName) LIKE LOWER(CONCAT('%', :search, '%')))")
    org.springframework.data.domain.Page<User> findActiveBySearch(@Param("search") String search, org.springframework.data.domain.Pageable pageable);
}
