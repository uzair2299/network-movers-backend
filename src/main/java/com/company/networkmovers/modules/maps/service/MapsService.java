package com.company.networkmovers.modules.maps.service;

import java.util.UUID;

import com.company.networkmovers.modules.maps.dto.request.MapsRequest;
import com.company.networkmovers.modules.maps.dto.response.MapsResponse;
import java.util.List;

public interface MapsService {
    MapsResponse create(MapsRequest request);
    MapsResponse findById(UUID id);
    List<MapsResponse> findAll();
    MapsResponse update(UUID id, MapsRequest request);
    void delete(UUID id);
}
