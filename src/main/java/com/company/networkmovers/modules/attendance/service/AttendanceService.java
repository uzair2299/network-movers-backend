package com.company.networkmovers.modules.attendance.service;

import java.util.UUID;

import com.company.networkmovers.modules.attendance.dto.request.AttendanceRequest;
import com.company.networkmovers.modules.attendance.dto.response.AttendanceResponse;
import java.util.List;

public interface AttendanceService {
    AttendanceResponse create(AttendanceRequest request);
    AttendanceResponse findById(UUID id);
    List<AttendanceResponse> findAll();
    AttendanceResponse update(UUID id, AttendanceRequest request);
    void delete(UUID id);
}
