package com.company.networkmovers.modules.coupon.service;

import com.company.networkmovers.modules.coupon.dto.request.CouponRequest;
import com.company.networkmovers.modules.coupon.dto.response.CouponResponse;
import java.util.List;

public interface CouponService {
    CouponResponse create(CouponRequest request);
    CouponResponse findById(Long id);
    List<CouponResponse> findAll();
    CouponResponse update(Long id, CouponRequest request);
    void delete(Long id);
}
