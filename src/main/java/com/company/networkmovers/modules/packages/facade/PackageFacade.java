package com.company.networkmovers.modules.packages.facade;

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

    public PackageResponse findById(Long id) {
        return service.findById(id);
    }

    public List<PackageResponse> findAll() {
        return service.findAll();
    }

    public PackageResponse update(Long id, PackageRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
