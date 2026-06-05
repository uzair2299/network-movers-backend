package com.company.networkmovers.modules.lookup.service;

import java.util.UUID;

import com.company.networkmovers.modules.lookup.dto.request.LookupRequest;
import com.company.networkmovers.modules.lookup.dto.response.LookupResponse;
import java.util.List;

public interface LookupService {
    LookupResponse create(LookupRequest request);
    LookupResponse findById(UUID id);
    List<LookupResponse> findAll();
    LookupResponse update(UUID id, LookupRequest request);
    void delete(UUID id);
}
