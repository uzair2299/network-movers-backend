package com.company.networkmovers.modules.dispatch.service;

import com.company.networkmovers.modules.dispatch.dto.request.DispatchRequest;
import com.company.networkmovers.modules.dispatch.dto.response.DispatchResponse;
import java.util.List;

public interface DispatchService {
    DispatchResponse create(DispatchRequest request);
    DispatchResponse findById(Long id);
    List<DispatchResponse> findAll();
    DispatchResponse update(Long id, DispatchRequest request);
    void delete(Long id);
}
