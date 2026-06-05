package com.company.networkmovers.modules.customer.facade;

import java.util.UUID;

import com.company.networkmovers.modules.customer.dto.request.CustomerRequest;
import com.company.networkmovers.modules.customer.dto.response.CustomerResponse;
import com.company.networkmovers.modules.customer.service.CustomerService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CustomerFacade {

    private final CustomerService service;

    public CustomerFacade(CustomerService service) {
        this.service = service;
    }

    public CustomerResponse create(CustomerRequest request) {
        return service.create(request);
    }

    public CustomerResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<CustomerResponse> findAll() {
        return service.findAll();
    }

    public CustomerResponse update(UUID id, CustomerRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
