package com.company.networkmovers.modules.coupon.mapper;

import com.company.networkmovers.modules.coupon.entity.CouponEntity;
import com.company.networkmovers.modules.coupon.dto.request.CouponRequest;
import com.company.networkmovers.modules.coupon.dto.response.CouponResponse;
import org.springframework.stereotype.Component;

@Component
public class CouponMapper {

    public CouponEntity toEntity(CouponRequest request) {
        if (request == null) return null;
        return CouponEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public CouponResponse toResponse(CouponEntity entity) {
        if (entity == null) return null;
        return CouponResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
