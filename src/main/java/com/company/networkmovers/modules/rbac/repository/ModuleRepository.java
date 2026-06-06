package com.company.networkmovers.modules.rbac.repository;

import com.company.networkmovers.modules.rbac.entity.Module;
import com.company.networkmovers.shared.repository.BaseLookupRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository("modulesModuleRepository")
public interface ModuleRepository extends BaseLookupRepository<Module> {
    Optional<Module> findByName(String name);
}
