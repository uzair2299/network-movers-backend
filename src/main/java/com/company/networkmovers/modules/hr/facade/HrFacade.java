package com.company.networkmovers.modules.hr.facade;

import com.company.networkmovers.modules.hr.dto.request.HrRequest;
import com.company.networkmovers.modules.hr.dto.response.HrResponse;
import com.company.networkmovers.modules.hr.service.HrService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class HrFacade {

    private final HrService service;

    public HrFacade(HrService service) {
        this.service = service;
    }

    public HrResponse create(HrRequest request) {
        return service.create(request);
    }

    public HrResponse findById(Long id) {
        return service.findById(id);
    }

    public List<HrResponse> findAll() {
        return service.findAll();
    }

    public HrResponse update(Long id, HrRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
