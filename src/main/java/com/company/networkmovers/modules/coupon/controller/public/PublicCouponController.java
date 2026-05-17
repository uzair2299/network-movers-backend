package com.company.networkmovers.modules.coupon.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.coupon.dto.response.CouponResponse;
import com.company.networkmovers.modules.coupon.service.CouponService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/coupon")
public class PublicCouponController {

    private final CouponService service;

    public PublicCouponController(CouponService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CouponResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
