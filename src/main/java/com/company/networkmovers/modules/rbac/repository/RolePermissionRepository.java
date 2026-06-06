package com.company.networkmovers.modules.rbac.repository;

import com.company.networkmovers.modules.rbac.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("modulesRolePermissionRepository")
public interface RolePermissionRepository extends JpaRepository<RolePermission, UUID> {

    /** Used by NavigationService to load permissions for a user's roles */
    @Query("SELECT DISTINCT rp.permission.name FROM RolePermission rp WHERE rp.role.name IN :roleNames AND rp.deleted = false AND rp.active = true")
    List<String> findPermissionNamesByRoleNames(@Param("roleNames") List<String> roleNames);

    /** All active (non-deleted) role-permission assignments for a given role */
    @Query("SELECT rp FROM RolePermission rp WHERE rp.role.id = :roleId AND rp.deleted = false")
    List<RolePermission> findByRoleId(@Param("roleId") UUID roleId);

    /** All active (non-deleted) role-permission assignments for a given permission */
    @Query("SELECT rp FROM RolePermission rp WHERE rp.permission.id = :permissionId AND rp.deleted = false")
    List<RolePermission> findByPermissionId(@Param("permissionId") UUID permissionId);

    /** Check if an assignment already exists (non-deleted) */
    @Query("SELECT rp FROM RolePermission rp WHERE rp.role.id = :roleId AND rp.permission.id = :permissionId AND rp.deleted = false")
    Optional<RolePermission> findByRoleIdAndPermissionId(@Param("roleId") UUID roleId, @Param("permissionId") UUID permissionId);

    /** All active assignments (paged from service layer) */
    @Query("SELECT rp FROM RolePermission rp WHERE rp.deleted = false ORDER BY rp.createdAt DESC")
    List<RolePermission> findAllActive();
}
