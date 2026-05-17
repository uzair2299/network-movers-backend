package com.company.networkmovers.modules.lookup.service;

import com.company.networkmovers.modules.lookup.dto.request.LookupRequest;
import com.company.networkmovers.modules.lookup.dto.response.LookupResponse;
import java.util.List;

public interface LookupService {
    LookupResponse create(LookupRequest request);
    LookupResponse findById(Long id);
    List<LookupResponse> findAll();
    LookupResponse update(Long id, LookupRequest request);
    void delete(Long id);
}
