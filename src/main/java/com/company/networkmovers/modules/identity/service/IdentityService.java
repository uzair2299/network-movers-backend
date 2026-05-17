package com.company.networkmovers.modules.identity.service;

import com.company.networkmovers.modules.identity.dto.request.IdentityRequest;
import com.company.networkmovers.modules.identity.dto.response.IdentityResponse;
import java.util.List;

public interface IdentityService {
    IdentityResponse create(IdentityRequest request);
    IdentityResponse findById(Long id);
    List<IdentityResponse> findAll();
    IdentityResponse update(Long id, IdentityRequest request);
    void delete(Long id);
}
