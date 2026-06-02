package com.company.networkmovers.security.rbac.service;

import com.company.networkmovers.security.rbac.dto.request.RoleRequest;
import com.company.networkmovers.security.rbac.dto.response.RoleResponse;
import com.company.networkmovers.shared.service.GenericLookupService;

public interface RoleService extends GenericLookupService<RoleRequest, RoleResponse> {
}
