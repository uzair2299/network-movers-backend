package com.company.networkmovers.modules.identity.service;

import com.company.networkmovers.modules.identity.dto.request.AdminUserRequest;
import com.company.networkmovers.modules.identity.dto.response.AdminUserResponse;

import java.util.List;

public interface AdminUserService {
    AdminUserResponse create(AdminUserRequest request);
    AdminUserResponse update(Long id, AdminUserRequest request);
    AdminUserResponse toggleActive(Long id);
    void softDelete(Long id);
    org.springframework.data.domain.Page<AdminUserResponse> getAll(com.company.networkmovers.shared.dto.RequestParamDto requestParams);
    org.springframework.data.domain.Page<AdminUserResponse> getAllActive(com.company.networkmovers.shared.dto.RequestParamDto requestParams);
    AdminUserResponse findById(Long id);
}
