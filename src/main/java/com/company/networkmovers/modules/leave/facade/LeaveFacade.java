package com.company.networkmovers.modules.leave.facade;

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

    public LeaveResponse findById(Long id) {
        return service.findById(id);
    }

    public List<LeaveResponse> findAll() {
        return service.findAll();
    }

    public LeaveResponse update(Long id, LeaveRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
