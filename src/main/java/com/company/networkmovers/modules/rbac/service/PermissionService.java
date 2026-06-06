package com.company.networkmovers.modules.rbac.service;

import com.company.networkmovers.modules.rbac.dto.request.PermissionRequest;
import com.company.networkmovers.modules.rbac.dto.response.PermissionResponse;
import com.company.networkmovers.shared.service.GenericLookupService;

public interface PermissionService extends GenericLookupService<PermissionRequest, PermissionResponse> {
}
