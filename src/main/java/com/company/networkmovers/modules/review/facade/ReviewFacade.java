package com.company.networkmovers.modules.review.facade;

import com.company.networkmovers.modules.review.dto.request.ReviewRequest;
import com.company.networkmovers.modules.review.dto.response.ReviewResponse;
import com.company.networkmovers.modules.review.service.ReviewService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ReviewFacade {

    private final ReviewService service;

    public ReviewFacade(ReviewService service) {
        this.service = service;
    }

    public ReviewResponse create(ReviewRequest request) {
        return service.create(request);
    }

    public ReviewResponse findById(Long id) {
        return service.findById(id);
    }

    public List<ReviewResponse> findAll() {
        return service.findAll();
    }

    public ReviewResponse update(Long id, ReviewRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
