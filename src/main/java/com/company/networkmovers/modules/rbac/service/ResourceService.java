package com.company.networkmovers.modules.rbac.service;

import com.company.networkmovers.modules.rbac.dto.request.ResourceRequest;
import com.company.networkmovers.modules.rbac.dto.response.ResourceResponse;
import com.company.networkmovers.shared.service.GenericLookupService;

public interface ResourceService extends GenericLookupService<ResourceRequest, ResourceResponse> {
}
