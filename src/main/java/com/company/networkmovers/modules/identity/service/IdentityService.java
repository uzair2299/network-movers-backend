package com.company.networkmovers.modules.identity.service;

import java.util.UUID;

import com.company.networkmovers.modules.identity.dto.request.IdentityRequest;
import com.company.networkmovers.modules.identity.dto.response.IdentityResponse;
import java.util.List;

public interface IdentityService {
    IdentityResponse create(IdentityRequest request);
    IdentityResponse findById(UUID id);
    List<IdentityResponse> findAll();
    IdentityResponse update(UUID id, IdentityRequest request);
    void delete(UUID id);
}
