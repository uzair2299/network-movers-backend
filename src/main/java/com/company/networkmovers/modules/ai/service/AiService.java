package com.company.networkmovers.modules.ai.service;

import java.util.UUID;

import com.company.networkmovers.modules.ai.dto.request.AiRequest;
import com.company.networkmovers.modules.ai.dto.response.AiResponse;
import java.util.List;

public interface AiService {
    AiResponse create(AiRequest request);
    AiResponse findById(UUID id);
    List<AiResponse> findAll();
    AiResponse update(UUID id, AiRequest request);
    void delete(UUID id);
}
