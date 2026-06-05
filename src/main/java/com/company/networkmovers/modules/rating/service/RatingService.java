package com.company.networkmovers.modules.rating.service;

import java.util.UUID;

import com.company.networkmovers.modules.rating.dto.request.RatingRequest;
import com.company.networkmovers.modules.rating.dto.response.RatingResponse;
import java.util.List;

public interface RatingService {
    RatingResponse create(RatingRequest request);
    RatingResponse findById(UUID id);
    List<RatingResponse> findAll();
    RatingResponse update(UUID id, RatingRequest request);
    void delete(UUID id);
}
