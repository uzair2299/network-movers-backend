package com.company.networkmovers.modules.leave.service;

import com.company.networkmovers.modules.leave.dto.request.LeaveRequest;
import com.company.networkmovers.modules.leave.dto.response.LeaveResponse;
import java.util.List;

public interface LeaveService {
    LeaveResponse create(LeaveRequest request);
    LeaveResponse findById(Long id);
    List<LeaveResponse> findAll();
    LeaveResponse update(Long id, LeaveRequest request);
    void delete(Long id);
}
