package com.company.networkmovers.modules.communication.facade;

import com.company.networkmovers.modules.communication.dto.request.CommunicationRequest;
import com.company.networkmovers.modules.communication.dto.response.CommunicationResponse;
import com.company.networkmovers.modules.communication.service.CommunicationService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CommunicationFacade {

    private final CommunicationService service;

    public CommunicationFacade(CommunicationService service) {
        this.service = service;
    }

    public CommunicationResponse create(CommunicationRequest request) {
        return service.create(request);
    }

    public CommunicationResponse findById(Long id) {
        return service.findById(id);
    }

    public List<CommunicationResponse> findAll() {
        return service.findAll();
    }

    public CommunicationResponse update(Long id, CommunicationRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
