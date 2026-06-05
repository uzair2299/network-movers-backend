package com.company.networkmovers.modules.coupon.service;

import java.util.UUID;

import com.company.networkmovers.modules.coupon.dto.request.CouponRequest;
import com.company.networkmovers.modules.coupon.dto.response.CouponResponse;
import java.util.List;

public interface CouponService {
    CouponResponse create(CouponRequest request);
    CouponResponse findById(UUID id);
    List<CouponResponse> findAll();
    CouponResponse update(UUID id, CouponRequest request);
    void delete(UUID id);
}
