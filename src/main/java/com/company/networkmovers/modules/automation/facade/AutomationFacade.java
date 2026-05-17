package com.company.networkmovers.modules.automation.facade;

import com.company.networkmovers.modules.automation.dto.request.AutomationRequest;
import com.company.networkmovers.modules.automation.dto.response.AutomationResponse;
import com.company.networkmovers.modules.automation.service.AutomationService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AutomationFacade {

    private final AutomationService service;

    public AutomationFacade(AutomationService service) {
        this.service = service;
    }

    public AutomationResponse create(AutomationRequest request) {
        return service.create(request);
    }

    public AutomationResponse findById(Long id) {
        return service.findById(id);
    }

    public List<AutomationResponse> findAll() {
        return service.findAll();
    }

    public AutomationResponse update(Long id, AutomationRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
