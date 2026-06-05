package com.company.networkmovers.modules.dispatcher.service;

import java.util.UUID;

import com.company.networkmovers.modules.dispatcher.dto.request.DispatcherRequest;
import com.company.networkmovers.modules.dispatcher.dto.response.DispatcherResponse;
import java.util.List;

public interface DispatcherService {
    DispatcherResponse create(DispatcherRequest request);
    DispatcherResponse findById(UUID id);
    List<DispatcherResponse> findAll();
    DispatcherResponse update(UUID id, DispatcherRequest request);
    void delete(UUID id);
}
