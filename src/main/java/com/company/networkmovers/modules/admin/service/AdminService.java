package com.company.networkmovers.modules.admin.service;

import java.util.UUID;

import com.company.networkmovers.modules.admin.dto.request.AdminRequest;
import com.company.networkmovers.modules.admin.dto.response.AdminResponse;
import java.util.List;

public interface AdminService {
    AdminResponse create(AdminRequest request);
    AdminResponse findById(UUID id);
    List<AdminResponse> findAll();
    AdminResponse update(UUID id, AdminRequest request);
    void delete(UUID id);
}
