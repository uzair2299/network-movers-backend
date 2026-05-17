package com.company.networkmovers.modules.customer.facade;

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

    public CustomerResponse findById(Long id) {
        return service.findById(id);
    }

    public List<CustomerResponse> findAll() {
        return service.findAll();
    }

    public CustomerResponse update(Long id, CustomerRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
