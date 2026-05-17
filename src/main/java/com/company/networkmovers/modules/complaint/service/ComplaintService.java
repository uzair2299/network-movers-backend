package com.company.networkmovers.modules.complaint.service;

import com.company.networkmovers.modules.complaint.dto.request.ComplaintRequest;
import com.company.networkmovers.modules.complaint.dto.response.ComplaintResponse;
import java.util.List;

public interface ComplaintService {
    ComplaintResponse create(ComplaintRequest request);
    ComplaintResponse findById(Long id);
    List<ComplaintResponse> findAll();
    ComplaintResponse update(Long id, ComplaintRequest request);
    void delete(Long id);
}
