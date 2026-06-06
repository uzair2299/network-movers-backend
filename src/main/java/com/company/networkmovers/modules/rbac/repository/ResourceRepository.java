package com.company.networkmovers.modules.rbac.repository;

import com.company.networkmovers.modules.rbac.entity.Resource;
import com.company.networkmovers.shared.repository.BaseLookupRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository("modulesResourceRepository")
public interface ResourceRepository extends BaseLookupRepository<Resource> {
    Optional<Resource> findByName(String name);
}
