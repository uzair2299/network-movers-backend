package com.company.networkmovers.modules.fraud.service;

import java.util.UUID;

import com.company.networkmovers.modules.fraud.dto.request.FraudRequest;
import com.company.networkmovers.modules.fraud.dto.response.FraudResponse;
import java.util.List;

public interface FraudService {
    FraudResponse create(FraudRequest request);
    FraudResponse findById(UUID id);
    List<FraudResponse> findAll();
    FraudResponse update(UUID id, FraudRequest request);
    void delete(UUID id);
}
