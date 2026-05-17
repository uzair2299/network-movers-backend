package com.company.networkmovers.modules.rating.service;

import com.company.networkmovers.modules.rating.dto.request.RatingRequest;
import com.company.networkmovers.modules.rating.dto.response.RatingResponse;
import java.util.List;

public interface RatingService {
    RatingResponse create(RatingRequest request);
    RatingResponse findById(Long id);
    List<RatingResponse> findAll();
    RatingResponse update(Long id, RatingRequest request);
    void delete(Long id);
}
