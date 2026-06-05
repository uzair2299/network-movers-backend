package com.company.networkmovers.modules.customer.service;

import java.util.UUID;

import com.company.networkmovers.modules.customer.dto.request.CustomerRequest;
import com.company.networkmovers.modules.customer.dto.response.CustomerResponse;
import java.util.List;

public interface CustomerService {
    CustomerResponse create(CustomerRequest request);
    CustomerResponse findById(UUID id);
    List<CustomerResponse> findAll();
    CustomerResponse update(UUID id, CustomerRequest request);
    void delete(UUID id);
}
