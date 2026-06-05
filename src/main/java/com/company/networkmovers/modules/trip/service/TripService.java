package com.company.networkmovers.modules.trip.service;

import java.util.UUID;

import com.company.networkmovers.modules.trip.dto.request.TripRequest;
import com.company.networkmovers.modules.trip.dto.response.TripResponse;
import java.util.List;

public interface TripService {
    TripResponse create(TripRequest request);
    TripResponse findById(UUID id);
    List<TripResponse> findAll();
    TripResponse update(UUID id, TripRequest request);
    void delete(UUID id);
}
