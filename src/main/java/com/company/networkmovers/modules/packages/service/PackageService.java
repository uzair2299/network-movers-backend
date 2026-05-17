package com.company.networkmovers.modules.packages.service;

import com.company.networkmovers.modules.packages.dto.request.PackageRequest;
import com.company.networkmovers.modules.packages.dto.response.PackageResponse;
import java.util.List;

public interface PackageService {
    PackageResponse create(PackageRequest request);
    PackageResponse findById(Long id);
    List<PackageResponse> findAll();
    PackageResponse update(Long id, PackageRequest request);
    void delete(Long id);
}
