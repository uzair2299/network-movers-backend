package com.company.networkmovers.modules.contract.service;

import java.util.UUID;

import com.company.networkmovers.modules.contract.dto.request.ContractRequest;
import com.company.networkmovers.modules.contract.dto.response.ContractResponse;
import java.util.List;

public interface ContractService {
    ContractResponse create(ContractRequest request);
    ContractResponse findById(UUID id);
    List<ContractResponse> findAll();
    ContractResponse update(UUID id, ContractRequest request);
    void delete(UUID id);
}
