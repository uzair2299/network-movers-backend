package com.company.networkmovers.modules.subscription.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionRequest {
    private String name;
    private String description;
}
