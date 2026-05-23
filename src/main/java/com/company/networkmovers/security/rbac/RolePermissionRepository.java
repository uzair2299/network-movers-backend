package com.company.networkmovers.security.rbac;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {

    @Query("SELECT DISTINCT rp.permission.name FROM RolePermission rp WHERE rp.role.name IN :roleNames")
    List<String> findPermissionNamesByRoleNames(@Param("roleNames") List<String> roleNames);
}
