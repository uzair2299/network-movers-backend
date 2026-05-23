package com.company.networkmovers.modules.navigation.dto.response;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItemResponse {
    private Long id;
    private String name;
    private String icon;
    private String path;
    private Integer sortOrder;
    private List<MenuItemResponse> children;
}
