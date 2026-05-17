package com.company.networkmovers.modules.support.facade;

import com.company.networkmovers.modules.support.dto.request.SupportRequest;
import com.company.networkmovers.modules.support.dto.response.SupportResponse;
import com.company.networkmovers.modules.support.service.SupportService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class SupportFacade {

    private final SupportService service;

    public SupportFacade(SupportService service) {
        this.service = service;
    }

    public SupportResponse create(SupportRequest request) {
        return service.create(request);
    }

    public SupportResponse findById(Long id) {
        return service.findById(id);
    }

    public List<SupportResponse> findAll() {
        return service.findAll();
    }

    public SupportResponse update(Long id, SupportRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
