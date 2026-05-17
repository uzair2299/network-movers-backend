package com.company.networkmovers.modules.customer.service;

import com.company.networkmovers.modules.customer.dto.request.CustomerRequest;
import com.company.networkmovers.modules.customer.dto.response.CustomerResponse;
import java.util.List;

public interface CustomerService {
    CustomerResponse create(CustomerRequest request);
    CustomerResponse findById(Long id);
    List<CustomerResponse> findAll();
    CustomerResponse update(Long id, CustomerRequest request);
    void delete(Long id);
}
