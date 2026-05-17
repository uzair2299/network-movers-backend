package com.company.networkmovers.modules.trip.service;

import com.company.networkmovers.modules.trip.dto.request.TripRequest;
import com.company.networkmovers.modules.trip.dto.response.TripResponse;
import java.util.List;

public interface TripService {
    TripResponse create(TripRequest request);
    TripResponse findById(Long id);
    List<TripResponse> findAll();
    TripResponse update(Long id, TripRequest request);
    void delete(Long id);
}
