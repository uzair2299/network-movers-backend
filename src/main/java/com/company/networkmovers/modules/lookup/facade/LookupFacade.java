package com.company.networkmovers.modules.lookup.facade;

import com.company.networkmovers.modules.lookup.dto.request.LookupRequest;
import com.company.networkmovers.modules.lookup.dto.response.LookupResponse;
import com.company.networkmovers.modules.lookup.service.LookupService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class LookupFacade {

    private final LookupService service;

    public LookupFacade(LookupService service) {
        this.service = service;
    }

    public LookupResponse create(LookupRequest request) {
        return service.create(request);
    }

    public LookupResponse findById(Long id) {
        return service.findById(id);
    }

    public List<LookupResponse> findAll() {
        return service.findAll();
    }

    public LookupResponse update(Long id, LookupRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
