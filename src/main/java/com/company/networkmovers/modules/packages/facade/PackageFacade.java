package com.company.networkmovers.modules.packages.facade;

import java.util.UUID;

import com.company.networkmovers.modules.packages.dto.request.PackageRequest;
import com.company.networkmovers.modules.packages.dto.response.PackageResponse;
import com.company.networkmovers.modules.packages.service.PackageService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PackageFacade {

    private final PackageService service;

    public PackageFacade(PackageService service) {
        this.service = service;
    }

    public PackageResponse create(PackageRequest request) {
        return service.create(request);
    }

    public PackageResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<PackageResponse> findAll() {
        return service.findAll();
    }

    public PackageResponse update(UUID id, PackageRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
