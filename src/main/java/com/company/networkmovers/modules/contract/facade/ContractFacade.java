package com.company.networkmovers.modules.contract.facade;

import java.util.UUID;

import com.company.networkmovers.modules.contract.dto.request.ContractRequest;
import com.company.networkmovers.modules.contract.dto.response.ContractResponse;
import com.company.networkmovers.modules.contract.service.ContractService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ContractFacade {

    private final ContractService service;

    public ContractFacade(ContractService service) {
        this.service = service;
    }

    public ContractResponse create(ContractRequest request) {
        return service.create(request);
    }

    public ContractResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<ContractResponse> findAll() {
        return service.findAll();
    }

    public ContractResponse update(UUID id, ContractRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
