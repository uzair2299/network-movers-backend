package com.company.networkmovers.modules.identity.facade;

import com.company.networkmovers.modules.identity.dto.request.IdentityRequest;
import com.company.networkmovers.modules.identity.dto.response.IdentityResponse;
import com.company.networkmovers.modules.identity.service.IdentityService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class IdentityFacade {

    private final IdentityService service;

    public IdentityFacade(IdentityService service) {
        this.service = service;
    }

    public IdentityResponse create(IdentityRequest request) {
        return service.create(request);
    }

    public IdentityResponse findById(Long id) {
        return service.findById(id);
    }

    public List<IdentityResponse> findAll() {
        return service.findAll();
    }

    public IdentityResponse update(Long id, IdentityRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
