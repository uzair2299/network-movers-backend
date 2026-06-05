package com.company.networkmovers.modules.review.service;

import java.util.UUID;

import com.company.networkmovers.modules.review.dto.request.ReviewRequest;
import com.company.networkmovers.modules.review.dto.response.ReviewResponse;
import java.util.List;

public interface ReviewService {
    ReviewResponse create(ReviewRequest request);
    ReviewResponse findById(UUID id);
    List<ReviewResponse> findAll();
    ReviewResponse update(UUID id, ReviewRequest request);
    void delete(UUID id);
}
