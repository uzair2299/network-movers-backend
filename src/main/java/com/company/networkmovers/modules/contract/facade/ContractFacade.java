package com.company.networkmovers.modules.contract.facade;

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

    public ContractResponse findById(Long id) {
        return service.findById(id);
    }

    public List<ContractResponse> findAll() {
        return service.findAll();
    }

    public ContractResponse update(Long id, ContractRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
