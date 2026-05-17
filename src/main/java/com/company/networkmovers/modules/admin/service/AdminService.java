package com.company.networkmovers.modules.admin.service;

import com.company.networkmovers.modules.admin.dto.request.AdminRequest;
import com.company.networkmovers.modules.admin.dto.response.AdminResponse;
import java.util.List;

public interface AdminService {
    AdminResponse create(AdminRequest request);
    AdminResponse findById(Long id);
    List<AdminResponse> findAll();
    AdminResponse update(Long id, AdminRequest request);
    void delete(Long id);
}
