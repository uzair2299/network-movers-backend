package com.company.networkmovers.modules.complaint.service;

import java.util.UUID;

import com.company.networkmovers.modules.complaint.dto.request.ComplaintRequest;
import com.company.networkmovers.modules.complaint.dto.response.ComplaintResponse;
import java.util.List;

public interface ComplaintService {
    ComplaintResponse create(ComplaintRequest request);
    ComplaintResponse findById(UUID id);
    List<ComplaintResponse> findAll();
    ComplaintResponse update(UUID id, ComplaintRequest request);
    void delete(UUID id);
}
