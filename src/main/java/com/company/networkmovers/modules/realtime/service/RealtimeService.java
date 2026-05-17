package com.company.networkmovers.modules.realtime.service;

import com.company.networkmovers.modules.realtime.dto.request.RealtimeRequest;
import com.company.networkmovers.modules.realtime.dto.response.RealtimeResponse;
import java.util.List;

public interface RealtimeService {
    RealtimeResponse create(RealtimeRequest request);
    RealtimeResponse findById(Long id);
    List<RealtimeResponse> findAll();
    RealtimeResponse update(Long id, RealtimeRequest request);
    void delete(Long id);
}
