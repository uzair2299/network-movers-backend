package com.company.networkmovers.modules.coupon.facade;

import com.company.networkmovers.modules.coupon.dto.request.CouponRequest;
import com.company.networkmovers.modules.coupon.dto.response.CouponResponse;
import com.company.networkmovers.modules.coupon.service.CouponService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CouponFacade {

    private final CouponService service;

    public CouponFacade(CouponService service) {
        this.service = service;
    }

    public CouponResponse create(CouponRequest request) {
        return service.create(request);
    }

    public CouponResponse findById(Long id) {
        return service.findById(id);
    }

    public List<CouponResponse> findAll() {
        return service.findAll();
    }

    public CouponResponse update(Long id, CouponRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
