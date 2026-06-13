package com.company.networkmovers.modules.asset.mapper;

import com.company.networkmovers.modules.asset.entity.MoveAssetUsage;
import com.company.networkmovers.modules.asset.dto.request.MoveAssetUsageRequest;
import com.company.networkmovers.modules.asset.dto.response.MoveAssetUsageResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MoveAssetUsageMapper implements GenericMapper<MoveAssetUsage, MoveAssetUsageRequest, MoveAssetUsageResponse> {

    private final MoveAssetUsageItemMapper itemMapper;

    @Override
    public MoveAssetUsage toEntity(MoveAssetUsageRequest request) {
        if (request == null) return null;
        return MoveAssetUsage.builder()
                .bookingId(request.getBookingId())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .remarks(request.getRemarks())
                .status(request.getStatus())
                .build();
    }

    @Override
    public MoveAssetUsageResponse toResponse(MoveAssetUsage entity) {
        if (entity == null) return null;
        return MoveAssetUsageResponse.builder()
                .id(entity.getId())
                .bookingId(entity.getBookingId())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .remarks(entity.getRemarks())
                .status(entity.getStatus())
                .items(entity.getItems() == null ? new ArrayList<>() : entity.getItems().stream()
                        .map(itemMapper::toResponse)
                        .collect(Collectors.toList()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
