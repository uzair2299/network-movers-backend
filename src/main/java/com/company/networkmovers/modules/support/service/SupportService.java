package com.company.networkmovers.modules.support.service;

import java.util.UUID;

import com.company.networkmovers.modules.support.dto.request.SupportRequest;
import com.company.networkmovers.modules.support.dto.response.SupportResponse;
import java.util.List;

public interface SupportService {
    SupportResponse create(SupportRequest request);
    SupportResponse findById(UUID id);
    List<SupportResponse> findAll();
    SupportResponse update(UUID id, SupportRequest request);
    void delete(UUID id);
}
