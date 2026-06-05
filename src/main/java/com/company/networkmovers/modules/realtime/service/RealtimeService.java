package com.company.networkmovers.modules.realtime.service;

import java.util.UUID;

import com.company.networkmovers.modules.realtime.dto.request.RealtimeRequest;
import com.company.networkmovers.modules.realtime.dto.response.RealtimeResponse;
import java.util.List;

public interface RealtimeService {
    RealtimeResponse create(RealtimeRequest request);
    RealtimeResponse findById(UUID id);
    List<RealtimeResponse> findAll();
    RealtimeResponse update(UUID id, RealtimeRequest request);
    void delete(UUID id);
}
