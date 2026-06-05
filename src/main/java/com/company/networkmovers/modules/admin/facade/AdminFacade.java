package com.company.networkmovers.modules.admin.facade;

import java.util.UUID;

import com.company.networkmovers.modules.admin.dto.request.AdminRequest;
import com.company.networkmovers.modules.admin.dto.response.AdminResponse;
import com.company.networkmovers.modules.admin.service.AdminService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AdminFacade {

    private final AdminService service;

    public AdminFacade(AdminService service) {
        this.service = service;
    }

    public AdminResponse create(AdminRequest request) {
        return service.create(request);
    }

    public AdminResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<AdminResponse> findAll() {
        return service.findAll();
    }

    public AdminResponse update(UUID id, AdminRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
