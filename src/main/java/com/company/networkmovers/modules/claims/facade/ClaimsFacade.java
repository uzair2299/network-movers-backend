package com.company.networkmovers.modules.claims.facade;

import java.util.UUID;

import com.company.networkmovers.modules.claims.dto.request.ClaimsRequest;
import com.company.networkmovers.modules.claims.dto.response.ClaimsResponse;
import com.company.networkmovers.modules.claims.service.ClaimsService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ClaimsFacade {

    private final ClaimsService service;

    public ClaimsFacade(ClaimsService service) {
        this.service = service;
    }

    public ClaimsResponse create(ClaimsRequest request) {
        return service.create(request);
    }

    public ClaimsResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<ClaimsResponse> findAll() {
        return service.findAll();
    }

    public ClaimsResponse update(UUID id, ClaimsRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
