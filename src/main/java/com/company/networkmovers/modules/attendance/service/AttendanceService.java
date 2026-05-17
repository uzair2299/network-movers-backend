package com.company.networkmovers.modules.attendance.service;

import com.company.networkmovers.modules.attendance.dto.request.AttendanceRequest;
import com.company.networkmovers.modules.attendance.dto.response.AttendanceResponse;
import java.util.List;

public interface AttendanceService {
    AttendanceResponse create(AttendanceRequest request);
    AttendanceResponse findById(Long id);
    List<AttendanceResponse> findAll();
    AttendanceResponse update(Long id, AttendanceRequest request);
    void delete(Long id);
}
