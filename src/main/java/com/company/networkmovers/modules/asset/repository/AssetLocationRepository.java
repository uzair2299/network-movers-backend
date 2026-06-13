package com.company.networkmovers.modules.asset.repository;

import com.company.networkmovers.modules.asset.entity.AssetLocation;
import com.company.networkmovers.shared.repository.BaseLookupRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetLocationRepository extends BaseLookupRepository<AssetLocation> {
}
