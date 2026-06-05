package com.company.networkmovers.modules.packages.service;

import java.util.UUID;

import com.company.networkmovers.modules.packages.dto.request.PackageRequest;
import com.company.networkmovers.modules.packages.dto.response.PackageResponse;
import java.util.List;

public interface PackageService {
    PackageResponse create(PackageRequest request);
    PackageResponse findById(UUID id);
    List<PackageResponse> findAll();
    PackageResponse update(UUID id, PackageRequest request);
    void delete(UUID id);
}
