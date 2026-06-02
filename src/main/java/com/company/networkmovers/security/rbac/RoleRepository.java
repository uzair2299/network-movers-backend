package com.company.networkmovers.security.rbac;

import com.company.networkmovers.shared.repository.BaseLookupRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends BaseLookupRepository<Role> {
    Optional<Role> findByName(String name);
}
