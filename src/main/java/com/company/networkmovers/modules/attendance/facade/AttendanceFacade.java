package com.company.networkmovers.modules.attendance.facade;

import com.company.networkmovers.modules.attendance.dto.request.AttendanceRequest;
import com.company.networkmovers.modules.attendance.dto.response.AttendanceResponse;
import com.company.networkmovers.modules.attendance.service.AttendanceService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AttendanceFacade {

    private final AttendanceService service;

    public AttendanceFacade(AttendanceService service) {
        this.service = service;
    }

    public AttendanceResponse create(AttendanceRequest request) {
        return service.create(request);
    }

    public AttendanceResponse findById(Long id) {
        return service.findById(id);
    }

    public List<AttendanceResponse> findAll() {
        return service.findAll();
    }

    public AttendanceResponse update(Long id, AttendanceRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
