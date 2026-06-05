package com.company.networkmovers.modules.automation.service;

import java.util.UUID;

import com.company.networkmovers.modules.automation.dto.request.AutomationRequest;
import com.company.networkmovers.modules.automation.dto.response.AutomationResponse;
import java.util.List;

public interface AutomationService {
    AutomationResponse create(AutomationRequest request);
    AutomationResponse findById(UUID id);
    List<AutomationResponse> findAll();
    AutomationResponse update(UUID id, AutomationRequest request);
    void delete(UUID id);
}
