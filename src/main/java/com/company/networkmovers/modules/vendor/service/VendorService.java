package com.company.networkmovers.modules.vendor.service;

import com.company.networkmovers.modules.vendor.dto.request.VendorRequest;
import com.company.networkmovers.modules.vendor.dto.response.VendorResponse;
import java.util.List;

public interface VendorService {
    VendorResponse create(VendorRequest request);
    VendorResponse findById(Long id);
    List<VendorResponse> findAll();
    VendorResponse update(Long id, VendorRequest request);
    void delete(Long id);
}
