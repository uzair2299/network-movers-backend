package com.company.networkmovers.modules.hr.service;

import java.util.UUID;

import com.company.networkmovers.modules.hr.dto.request.HrRequest;
import com.company.networkmovers.modules.hr.dto.response.HrResponse;
import java.util.List;

public interface HrService {
    HrResponse create(HrRequest request);
    HrResponse findById(UUID id);
    List<HrResponse> findAll();
    HrResponse update(UUID id, HrRequest request);
    void delete(UUID id);
}
