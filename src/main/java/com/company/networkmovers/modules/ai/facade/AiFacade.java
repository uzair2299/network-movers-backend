package com.company.networkmovers.modules.ai.facade;

import java.util.UUID;

import com.company.networkmovers.modules.ai.dto.request.AiRequest;
import com.company.networkmovers.modules.ai.dto.response.AiResponse;
import com.company.networkmovers.modules.ai.service.AiService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AiFacade {

    private final AiService service;

    public AiFacade(AiService service) {
        this.service = service;
    }

    public AiResponse create(AiRequest request) {
        return service.create(request);
    }

    public AiResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<AiResponse> findAll() {
        return service.findAll();
    }

    public AiResponse update(UUID id, AiRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
