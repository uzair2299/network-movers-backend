package com.company.networkmovers.modules.dispatch.service;

import java.util.UUID;

import com.company.networkmovers.modules.dispatch.dto.request.DispatchRequest;
import com.company.networkmovers.modules.dispatch.dto.response.DispatchResponse;
import java.util.List;

public interface DispatchService {
    DispatchResponse create(DispatchRequest request);
    DispatchResponse findById(UUID id);
    List<DispatchResponse> findAll();
    DispatchResponse update(UUID id, DispatchRequest request);
    void delete(UUID id);
}
