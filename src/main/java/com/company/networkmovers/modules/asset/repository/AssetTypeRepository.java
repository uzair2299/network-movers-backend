package com.company.networkmovers.modules.asset.repository;

import com.company.networkmovers.modules.asset.entity.AssetType;
import com.company.networkmovers.shared.repository.BaseLookupRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetTypeRepository extends BaseLookupRepository<AssetType> {
}
