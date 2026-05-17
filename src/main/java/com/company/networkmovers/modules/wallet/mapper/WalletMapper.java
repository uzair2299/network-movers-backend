package com.company.networkmovers.modules.wallet.mapper;

import com.company.networkmovers.modules.wallet.entity.WalletEntity;
import com.company.networkmovers.modules.wallet.dto.request.WalletRequest;
import com.company.networkmovers.modules.wallet.dto.response.WalletResponse;
import org.springframework.stereotype.Component;

@Component
public class WalletMapper {

    public WalletEntity toEntity(WalletRequest request) {
        if (request == null) return null;
        return WalletEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public WalletResponse toResponse(WalletEntity entity) {
        if (entity == null) return null;
        return WalletResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
