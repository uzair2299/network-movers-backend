package com.company.networkmovers.modules.leave.service;

import java.util.UUID;

import com.company.networkmovers.modules.leave.dto.request.LeaveRequest;
import com.company.networkmovers.modules.leave.dto.response.LeaveResponse;
import java.util.List;

public interface LeaveService {
    LeaveResponse create(LeaveRequest request);
    LeaveResponse findById(UUID id);
    List<LeaveResponse> findAll();
    LeaveResponse update(UUID id, LeaveRequest request);
    void delete(UUID id);
}
