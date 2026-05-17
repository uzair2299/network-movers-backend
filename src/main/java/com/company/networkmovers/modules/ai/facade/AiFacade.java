package com.company.networkmovers.modules.ai.facade;

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

    public AiResponse findById(Long id) {
        return service.findById(id);
    }

    public List<AiResponse> findAll() {
        return service.findAll();
    }

    public AiResponse update(Long id, AiRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
