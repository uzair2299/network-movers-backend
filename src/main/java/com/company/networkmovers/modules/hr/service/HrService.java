package com.company.networkmovers.modules.hr.service;

import com.company.networkmovers.modules.hr.dto.request.HrRequest;
import com.company.networkmovers.modules.hr.dto.response.HrResponse;
import java.util.List;

public interface HrService {
    HrResponse create(HrRequest request);
    HrResponse findById(Long id);
    List<HrResponse> findAll();
    HrResponse update(Long id, HrRequest request);
    void delete(Long id);
}
