package com.company.networkmovers.modules.maps.service;

import com.company.networkmovers.modules.maps.dto.request.MapsRequest;
import com.company.networkmovers.modules.maps.dto.response.MapsResponse;
import java.util.List;

public interface MapsService {
    MapsResponse create(MapsRequest request);
    MapsResponse findById(Long id);
    List<MapsResponse> findAll();
    MapsResponse update(Long id, MapsRequest request);
    void delete(Long id);
}
