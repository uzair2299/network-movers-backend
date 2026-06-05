package com.company.networkmovers.modules.vendor.service;

import java.util.UUID;

import com.company.networkmovers.modules.vendor.dto.request.VendorRequest;
import com.company.networkmovers.modules.vendor.dto.response.VendorResponse;
import java.util.List;

public interface VendorService {
    VendorResponse create(VendorRequest request);
    VendorResponse findById(UUID id);
    List<VendorResponse> findAll();
    VendorResponse update(UUID id, VendorRequest request);
    void delete(UUID id);
}
