package com.company.networkmovers.modules.wallet.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalletRequest {
    private String name;
    private String description;
}
