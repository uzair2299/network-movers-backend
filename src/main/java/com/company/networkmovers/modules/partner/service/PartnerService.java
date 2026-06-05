package com.company.networkmovers.modules.partner.service;

import java.util.UUID;

import com.company.networkmovers.modules.partner.dto.request.PartnerRequest;
import com.company.networkmovers.modules.partner.dto.response.PartnerResponse;
import java.util.List;

public interface PartnerService {
    PartnerResponse create(PartnerRequest request);
    PartnerResponse findById(UUID id);
    List<PartnerResponse> findAll();
    PartnerResponse update(UUID id, PartnerRequest request);
    void delete(UUID id);
}
