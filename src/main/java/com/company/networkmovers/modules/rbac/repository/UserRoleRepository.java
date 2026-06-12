package com.company.networkmovers.modules.rbac.repository;

import com.company.networkmovers.modules.rbac.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("modulesUserRoleRepository")
public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {
    List<UserRole> findByUserId(UUID userId);
    List<UserRole> findByRoleId(UUID roleId);
    void deleteByUserId(UUID userId);

    @Query("SELECT ur FROM UserRole ur WHERE ur.userId = :userId")
    Page<UserRole> findByUserIdPage(@Param("userId") UUID userId, Pageable pageable);

    @Query("SELECT ur FROM UserRole ur WHERE LOWER(ur.role.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<UserRole> findBySearch(@Param("search") String search, Pageable pageable);
}
