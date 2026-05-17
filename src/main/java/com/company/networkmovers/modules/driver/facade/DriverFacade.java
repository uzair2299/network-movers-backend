package com.company.networkmovers.modules.driver.facade;

import com.company.networkmovers.modules.driver.dto.request.DriverRequest;
import com.company.networkmovers.modules.driver.dto.response.DriverResponse;
import com.company.networkmovers.modules.driver.service.DriverService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DriverFacade {

    private final DriverService service;

    public DriverFacade(DriverService service) {
        this.service = service;
    }

    public DriverResponse create(DriverRequest request) {
        return service.create(request);
    }

    public DriverResponse findById(Long id) {
        return service.findById(id);
    }

    public List<DriverResponse> findAll() {
        return service.findAll();
    }

    public DriverResponse update(Long id, DriverRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
