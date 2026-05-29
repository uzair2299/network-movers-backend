package com.company.networkmovers.modules.navigation.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuItemResponse {
    private Long id;
    private String name;
    private String icon;
    private String path;
    private String section;
    private Long parentId;
    private Long permissionId;
    private Integer sortOrder;
    private boolean active;
    private List<MenuItemResponse> children;
}
