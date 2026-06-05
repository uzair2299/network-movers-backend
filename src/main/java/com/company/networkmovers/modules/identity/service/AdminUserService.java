package com.company.networkmovers.modules.identity.service;

import com.company.networkmovers.modules.identity.dto.request.AdminUserRequest;
import com.company.networkmovers.modules.identity.dto.response.AdminUserResponse;

import java.util.List;
import java.util.UUID;

public interface AdminUserService {
    AdminUserResponse create(AdminUserRequest request);
    AdminUserResponse update(UUID id, AdminUserRequest request);
    AdminUserResponse toggleActive(UUID id);
    void softDelete(UUID id);
    org.springframework.data.domain.Page<AdminUserResponse> getAll(com.company.networkmovers.shared.dto.RequestParamDto requestParams);
    org.springframework.data.domain.Page<AdminUserResponse> getAllActive(com.company.networkmovers.shared.dto.RequestParamDto requestParams);
    AdminUserResponse findById(UUID id);
}
