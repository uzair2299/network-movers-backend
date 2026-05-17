package com.company.networkmovers.modules.partner.service;

import com.company.networkmovers.modules.partner.dto.request.PartnerRequest;
import com.company.networkmovers.modules.partner.dto.response.PartnerResponse;
import java.util.List;

public interface PartnerService {
    PartnerResponse create(PartnerRequest request);
    PartnerResponse findById(Long id);
    List<PartnerResponse> findAll();
    PartnerResponse update(Long id, PartnerRequest request);
    void delete(Long id);
}
