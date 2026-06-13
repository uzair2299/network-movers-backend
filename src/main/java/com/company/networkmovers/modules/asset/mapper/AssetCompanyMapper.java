package com.company.networkmovers.modules.asset.mapper;

import com.company.networkmovers.modules.asset.entity.AssetCompany;
import com.company.networkmovers.modules.asset.dto.request.AssetCompanyRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetCompanyResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class AssetCompanyMapper implements GenericMapper<AssetCompany, AssetCompanyRequest, AssetCompanyResponse> {

    @Override
    public AssetCompany toEntity(AssetCompanyRequest request) {
        if (request == null) return null;
        return AssetCompany.builder()
                .name(request.getName())
                .code(request.getCode())
                .active(request.isActive())
                .build();
    }

    @Override
    public AssetCompanyResponse toResponse(AssetCompany entity) {
        if (entity == null) return null;
        return AssetCompanyResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .active(entity.isActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
