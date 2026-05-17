package com.company.networkmovers.modules.fraud.facade;

import com.company.networkmovers.modules.fraud.dto.request.FraudRequest;
import com.company.networkmovers.modules.fraud.dto.response.FraudResponse;
import com.company.networkmovers.modules.fraud.service.FraudService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class FraudFacade {

    private final FraudService service;

    public FraudFacade(FraudService service) {
        this.service = service;
    }

    public FraudResponse create(FraudRequest request) {
        return service.create(request);
    }

    public FraudResponse findById(Long id) {
        return service.findById(id);
    }

    public List<FraudResponse> findAll() {
        return service.findAll();
    }

    public FraudResponse update(Long id, FraudRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
