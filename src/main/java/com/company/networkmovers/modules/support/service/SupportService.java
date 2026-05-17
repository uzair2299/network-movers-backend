package com.company.networkmovers.modules.support.service;

import com.company.networkmovers.modules.support.dto.request.SupportRequest;
import com.company.networkmovers.modules.support.dto.response.SupportResponse;
import java.util.List;

public interface SupportService {
    SupportResponse create(SupportRequest request);
    SupportResponse findById(Long id);
    List<SupportResponse> findAll();
    SupportResponse update(Long id, SupportRequest request);
    void delete(Long id);
}
