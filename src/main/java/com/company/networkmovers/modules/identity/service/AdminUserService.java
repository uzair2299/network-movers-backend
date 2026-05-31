package com.company.networkmovers.modules.identity.service;

import com.company.networkmovers.modules.identity.dto.request.AdminUserRequest;
import com.company.networkmovers.modules.identity.dto.response.AdminUserResponse;

import java.util.List;

public interface AdminUserService {
    AdminUserResponse create(AdminUserRequest request, Long currentUserId);
    AdminUserResponse update(Long id, AdminUserRequest request, Long currentUserId);
    AdminUserResponse toggleActive(Long id, Long currentUserId);
    void softDelete(Long id, Long currentUserId);
    org.springframework.data.domain.Page<AdminUserResponse> getAll(com.company.networkmovers.shared.dto.RequestParamDto requestParams);
    List<AdminUserResponse> getAllActive();
    AdminUserResponse findById(Long id);
}
