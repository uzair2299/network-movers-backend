package com.company.networkmovers.modules.mover.service;

import com.company.networkmovers.modules.mover.dto.request.MoverRequest;
import com.company.networkmovers.modules.mover.dto.response.MoverResponse;
import java.util.List;

public interface MoverService {
    MoverResponse create(MoverRequest request);
    MoverResponse findById(Long id);
    List<MoverResponse> findAll();
    MoverResponse update(Long id, MoverRequest request);
    void delete(Long id);
}
