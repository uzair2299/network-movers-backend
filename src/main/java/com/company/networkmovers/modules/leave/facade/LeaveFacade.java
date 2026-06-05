package com.company.networkmovers.modules.leave.facade;

import java.util.UUID;

import com.company.networkmovers.modules.leave.dto.request.LeaveRequest;
import com.company.networkmovers.modules.leave.dto.response.LeaveResponse;
import com.company.networkmovers.modules.leave.service.LeaveService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class LeaveFacade {

    private final LeaveService service;

    public LeaveFacade(LeaveService service) {
        this.service = service;
    }

    public LeaveResponse create(LeaveRequest request) {
        return service.create(request);
    }

    public LeaveResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<LeaveResponse> findAll() {
        return service.findAll();
    }

    public LeaveResponse update(UUID id, LeaveRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
