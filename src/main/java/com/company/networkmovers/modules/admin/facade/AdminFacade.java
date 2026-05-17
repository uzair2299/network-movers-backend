package com.company.networkmovers.modules.admin.facade;

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

    public AdminResponse findById(Long id) {
        return service.findById(id);
    }

    public List<AdminResponse> findAll() {
        return service.findAll();
    }

    public AdminResponse update(Long id, AdminRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
