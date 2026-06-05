package com.company.networkmovers.modules.claims.service;

import java.util.UUID;

import com.company.networkmovers.modules.claims.dto.request.ClaimsRequest;
import com.company.networkmovers.modules.claims.dto.response.ClaimsResponse;
import java.util.List;

public interface ClaimsService {
    ClaimsResponse create(ClaimsRequest request);
    ClaimsResponse findById(UUID id);
    List<ClaimsResponse> findAll();
    ClaimsResponse update(UUID id, ClaimsRequest request);
    void delete(UUID id);
}
