package com.company.networkmovers.modules.driver.service;

import java.util.UUID;

import com.company.networkmovers.modules.driver.dto.request.DriverRequest;
import com.company.networkmovers.modules.driver.dto.response.DriverResponse;
import java.util.List;

public interface DriverService {
    DriverResponse create(DriverRequest request);
    DriverResponse findById(UUID id);
    List<DriverResponse> findAll();
    DriverResponse update(UUID id, DriverRequest request);
    void delete(UUID id);
}
