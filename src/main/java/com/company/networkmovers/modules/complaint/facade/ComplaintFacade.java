package com.company.networkmovers.modules.complaint.facade;

import com.company.networkmovers.modules.complaint.dto.request.ComplaintRequest;
import com.company.networkmovers.modules.complaint.dto.response.ComplaintResponse;
import com.company.networkmovers.modules.complaint.service.ComplaintService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ComplaintFacade {

    private final ComplaintService service;

    public ComplaintFacade(ComplaintService service) {
        this.service = service;
    }

    public ComplaintResponse create(ComplaintRequest request) {
        return service.create(request);
    }

    public ComplaintResponse findById(Long id) {
        return service.findById(id);
    }

    public List<ComplaintResponse> findAll() {
        return service.findAll();
    }

    public ComplaintResponse update(Long id, ComplaintRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
