package com.company.networkmovers.modules.rbac.service;

import com.company.networkmovers.modules.rbac.dto.request.RoleRequest;
import com.company.networkmovers.modules.rbac.dto.response.RoleResponse;
import com.company.networkmovers.shared.service.GenericLookupService;

public interface RoleService extends GenericLookupService<RoleRequest, RoleResponse> {
}
