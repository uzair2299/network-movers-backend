package com.company.networkmovers.modules.navigation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemRequest {

    @NotBlank(message = "Name is required")
    private String name;

    private String icon;

    private String path;

    @NotBlank(message = "Section is required")
    private String section;

    private Long parentId;

    @NotNull(message = "Sort order is required")
    private Integer sortOrder;

    private Long permissionId;

    @Builder.Default
    private boolean active = true;
}
