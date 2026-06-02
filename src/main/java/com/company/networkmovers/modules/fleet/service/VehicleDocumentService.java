package com.company.networkmovers.modules.fleet.service;

import com.company.networkmovers.modules.fleet.dto.request.VehicleDocumentRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleDocumentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface VehicleDocumentService {

    VehicleDocumentResponse create(VehicleDocumentRequest request);
    
    VehicleDocumentResponse update(UUID id, VehicleDocumentRequest request);

    VehicleDocumentResponse getById(UUID id);

    Page<VehicleDocumentResponse> getByVehicleId(UUID vehicleId, Pageable pageable);
    
    Page<VehicleDocumentResponse> getExpiringDocuments(int daysAhead, Pageable pageable);

    void delete(UUID id);
}
