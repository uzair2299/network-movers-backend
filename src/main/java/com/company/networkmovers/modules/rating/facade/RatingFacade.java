package com.company.networkmovers.modules.rating.facade;

import com.company.networkmovers.modules.rating.dto.request.RatingRequest;
import com.company.networkmovers.modules.rating.dto.response.RatingResponse;
import com.company.networkmovers.modules.rating.service.RatingService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class RatingFacade {

    private final RatingService service;

    public RatingFacade(RatingService service) {
        this.service = service;
    }

    public RatingResponse create(RatingRequest request) {
        return service.create(request);
    }

    public RatingResponse findById(Long id) {
        return service.findById(id);
    }

    public List<RatingResponse> findAll() {
        return service.findAll();
    }

    public RatingResponse update(Long id, RatingRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
