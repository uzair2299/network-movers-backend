package com.company.networkmovers.modules.communication.service;

import java.util.UUID;

import com.company.networkmovers.modules.communication.dto.request.CommunicationRequest;
import com.company.networkmovers.modules.communication.dto.response.CommunicationResponse;
import java.util.List;

public interface CommunicationService {
    CommunicationResponse create(CommunicationRequest request);
    CommunicationResponse findById(UUID id);
    List<CommunicationResponse> findAll();
    CommunicationResponse update(UUID id, CommunicationRequest request);
    void delete(UUID id);
}
