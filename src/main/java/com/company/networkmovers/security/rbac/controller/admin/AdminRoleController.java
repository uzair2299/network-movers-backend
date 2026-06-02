package com.company.networkmovers.security.rbac.controller.admin;

import com.company.networkmovers.security.rbac.dto.request.RoleRequest;
import com.company.networkmovers.security.rbac.dto.response.RoleResponse;
import com.company.networkmovers.security.rbac.service.RoleService;
import com.company.networkmovers.shared.controller.AbstractLookupController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/roles")
public class AdminRoleController extends AbstractLookupController<RoleRequest, RoleResponse> {

    public AdminRoleController(RoleService service) {
        super(service);
    }
}
