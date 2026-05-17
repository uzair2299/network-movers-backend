package com.company.networkmovers.modules.realtime.facade;

import com.company.networkmovers.modules.realtime.dto.request.RealtimeRequest;
import com.company.networkmovers.modules.realtime.dto.response.RealtimeResponse;
import com.company.networkmovers.modules.realtime.service.RealtimeService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class RealtimeFacade {

    private final RealtimeService service;

    public RealtimeFacade(RealtimeService service) {
        this.service = service;
    }

    public RealtimeResponse create(RealtimeRequest request) {
        return service.create(request);
    }

    public RealtimeResponse findById(Long id) {
        return service.findById(id);
    }

    public List<RealtimeResponse> findAll() {
        return service.findAll();
    }

    public RealtimeResponse update(Long id, RealtimeRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
