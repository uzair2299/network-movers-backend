package com.company.networkmovers.modules.automation.service;

import com.company.networkmovers.modules.automation.dto.request.AutomationRequest;
import com.company.networkmovers.modules.automation.dto.response.AutomationResponse;
import java.util.List;

public interface AutomationService {
    AutomationResponse create(AutomationRequest request);
    AutomationResponse findById(Long id);
    List<AutomationResponse> findAll();
    AutomationResponse update(Long id, AutomationRequest request);
    void delete(Long id);
}
