package com.company.networkmovers.modules.contract.service;

import com.company.networkmovers.modules.contract.dto.request.ContractRequest;
import com.company.networkmovers.modules.contract.dto.response.ContractResponse;
import java.util.List;

public interface ContractService {
    ContractResponse create(ContractRequest request);
    ContractResponse findById(Long id);
    List<ContractResponse> findAll();
    ContractResponse update(Long id, ContractRequest request);
    void delete(Long id);
}
