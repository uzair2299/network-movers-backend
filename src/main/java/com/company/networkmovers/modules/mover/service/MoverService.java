package com.company.networkmovers.modules.mover.service;

import java.util.UUID;

import com.company.networkmovers.modules.mover.dto.request.MoverRequest;
import com.company.networkmovers.modules.mover.dto.response.MoverResponse;
import java.util.List;

public interface MoverService {
    MoverResponse create(MoverRequest request);
    MoverResponse findById(UUID id);
    List<MoverResponse> findAll();
    MoverResponse update(UUID id, MoverRequest request);
    void delete(UUID id);
}
