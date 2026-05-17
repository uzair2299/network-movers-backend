package com.company.networkmovers.modules.driver.service;

import com.company.networkmovers.modules.driver.dto.request.DriverRequest;
import com.company.networkmovers.modules.driver.dto.response.DriverResponse;
import java.util.List;

public interface DriverService {
    DriverResponse create(DriverRequest request);
    DriverResponse findById(Long id);
    List<DriverResponse> findAll();
    DriverResponse update(Long id, DriverRequest request);
    void delete(Long id);
}
