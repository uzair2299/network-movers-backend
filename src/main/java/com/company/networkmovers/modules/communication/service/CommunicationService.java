package com.company.networkmovers.modules.communication.service;

import com.company.networkmovers.modules.communication.dto.request.CommunicationRequest;
import com.company.networkmovers.modules.communication.dto.response.CommunicationResponse;
import java.util.List;

public interface CommunicationService {
    CommunicationResponse create(CommunicationRequest request);
    CommunicationResponse findById(Long id);
    List<CommunicationResponse> findAll();
    CommunicationResponse update(Long id, CommunicationRequest request);
    void delete(Long id);
}
