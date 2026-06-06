package com.company.networkmovers.modules.rbac.service;

import com.company.networkmovers.modules.rbac.dto.request.ModuleRequest;
import com.company.networkmovers.modules.rbac.dto.response.ModuleResponse;
import com.company.networkmovers.shared.service.GenericLookupService;

public interface ModuleService extends GenericLookupService<ModuleRequest, ModuleResponse> {
}
