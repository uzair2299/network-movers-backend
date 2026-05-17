package com.company.networkmovers.modules.maps.facade;

import com.company.networkmovers.modules.maps.dto.request.MapsRequest;
import com.company.networkmovers.modules.maps.dto.response.MapsResponse;
import com.company.networkmovers.modules.maps.service.MapsService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class MapsFacade {

    private final MapsService service;

    public MapsFacade(MapsService service) {
        this.service = service;
    }

    public MapsResponse create(MapsRequest request) {
        return service.create(request);
    }

    public MapsResponse findById(Long id) {
        return service.findById(id);
    }

    public List<MapsResponse> findAll() {
        return service.findAll();
    }

    public MapsResponse update(Long id, MapsRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
