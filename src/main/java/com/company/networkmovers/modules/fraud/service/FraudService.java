package com.company.networkmovers.modules.fraud.service;

import com.company.networkmovers.modules.fraud.dto.request.FraudRequest;
import com.company.networkmovers.modules.fraud.dto.response.FraudResponse;
import java.util.List;

public interface FraudService {
    FraudResponse create(FraudRequest request);
    FraudResponse findById(Long id);
    List<FraudResponse> findAll();
    FraudResponse update(Long id, FraudRequest request);
    void delete(Long id);
}
