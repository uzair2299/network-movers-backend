package com.company.networkmovers.modules.rbac.repository;

import com.company.networkmovers.modules.rbac.entity.Role;
import com.company.networkmovers.shared.repository.BaseLookupRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository("modulesRoleRepository")
public interface RoleRepository extends BaseLookupRepository<Role> {
    Optional<Role> findByName(String name);
}
