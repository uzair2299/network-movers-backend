package com.company.networkmovers.modules.claims.service;

import com.company.networkmovers.modules.claims.dto.request.ClaimsRequest;
import com.company.networkmovers.modules.claims.dto.response.ClaimsResponse;
import java.util.List;

public interface ClaimsService {
    ClaimsResponse create(ClaimsRequest request);
    ClaimsResponse findById(Long id);
    List<ClaimsResponse> findAll();
    ClaimsResponse update(Long id, ClaimsRequest request);
    void delete(Long id);
}
