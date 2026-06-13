package com.company.networkmovers.modules.rbac.repository;

import com.company.networkmovers.modules.rbac.entity.Permission;
import com.company.networkmovers.shared.repository.BaseLookupRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("modulesPermissionRepository")
public interface PermissionRepository extends BaseLookupRepository<Permission> {
    Optional<Permission> findByName(String name);

    @Query("SELECT p FROM Permission p WHERE p.id IN :ids AND p.deleted = false")
    List<Permission> findAllActiveByIds(@Param("ids") Collection<UUID> ids);
}
