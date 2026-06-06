package com.company.networkmovers.modules.rbac.repository;

import com.company.networkmovers.modules.rbac.entity.Permission;
import com.company.networkmovers.shared.repository.BaseLookupRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository("modulesPermissionRepository")
public interface PermissionRepository extends BaseLookupRepository<Permission> {
    Optional<Permission> findByName(String name);
}
