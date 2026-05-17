package com.company.networkmovers.modules.coupon.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponRequest {
    private String name;
    private String description;
}
