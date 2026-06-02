package com.company.networkmovers.security.rbac.controller.admin;

import com.company.networkmovers.security.rbac.dto.request.RoleRequest;
import com.company.networkmovers.security.rbac.dto.response.RoleResponse;
import com.company.networkmovers.security.rbac.service.AdminRoleService;
import com.company.networkmovers.shared.controller.AbstractLookupController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/roles")
@Tag(name = "Admin Roles", description = "Endpoints for managing RBAC roles from the admin application")
public class AdminRoleController extends AbstractLookupController<RoleRequest, RoleResponse> {

    public AdminRoleController(AdminRoleService service) {
        super(service);
    }
}
