package com.company.networkmovers.modules.ai.service;

import com.company.networkmovers.modules.ai.dto.request.AiRequest;
import com.company.networkmovers.modules.ai.dto.response.AiResponse;
import java.util.List;

public interface AiService {
    AiResponse create(AiRequest request);
    AiResponse findById(Long id);
    List<AiResponse> findAll();
    AiResponse update(Long id, AiRequest request);
    void delete(Long id);
}
