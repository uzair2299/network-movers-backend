package com.company.networkmovers.modules.review.service;

import com.company.networkmovers.modules.review.dto.request.ReviewRequest;
import com.company.networkmovers.modules.review.dto.response.ReviewResponse;
import java.util.List;

public interface ReviewService {
    ReviewResponse create(ReviewRequest request);
    ReviewResponse findById(Long id);
    List<ReviewResponse> findAll();
    ReviewResponse update(Long id, ReviewRequest request);
    void delete(Long id);
}
