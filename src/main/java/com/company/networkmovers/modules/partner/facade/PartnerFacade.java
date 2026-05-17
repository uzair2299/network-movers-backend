package com.company.networkmovers.modules.partner.facade;

import com.company.networkmovers.modules.partner.dto.request.PartnerRequest;
import com.company.networkmovers.modules.partner.dto.response.PartnerResponse;
import com.company.networkmovers.modules.partner.service.PartnerService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PartnerFacade {

    private final PartnerService service;

    public PartnerFacade(PartnerService service) {
        this.service = service;
    }

    public PartnerResponse create(PartnerRequest request) {
        return service.create(request);
    }

    public PartnerResponse findById(Long id) {
        return service.findById(id);
    }

    public List<PartnerResponse> findAll() {
        return service.findAll();
    }

    public PartnerResponse update(Long id, PartnerRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
