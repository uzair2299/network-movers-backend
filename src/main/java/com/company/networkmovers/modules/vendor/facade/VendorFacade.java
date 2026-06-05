package com.company.networkmovers.modules.vendor.facade;

import java.util.UUID;

import com.company.networkmovers.modules.vendor.dto.request.VendorRequest;
import com.company.networkmovers.modules.vendor.dto.response.VendorResponse;
import com.company.networkmovers.modules.vendor.service.VendorService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class VendorFacade {

    private final VendorService service;

    public VendorFacade(VendorService service) {
        this.service = service;
    }

    public VendorResponse create(VendorRequest request) {
        return service.create(request);
    }

    public VendorResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<VendorResponse> findAll() {
        return service.findAll();
    }

    public VendorResponse update(UUID id, VendorRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
