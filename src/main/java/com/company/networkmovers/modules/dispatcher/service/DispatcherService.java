package com.company.networkmovers.modules.dispatcher.service;

import com.company.networkmovers.modules.dispatcher.dto.request.DispatcherRequest;
import com.company.networkmovers.modules.dispatcher.dto.response.DispatcherResponse;
import java.util.List;

public interface DispatcherService {
    DispatcherResponse create(DispatcherRequest request);
    DispatcherResponse findById(Long id);
    List<DispatcherResponse> findAll();
    DispatcherResponse update(Long id, DispatcherRequest request);
    void delete(Long id);
}
