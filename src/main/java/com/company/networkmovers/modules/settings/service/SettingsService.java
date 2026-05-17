package com.company.networkmovers.modules.settings.service;

import com.company.networkmovers.modules.settings.dto.request.SettingsRequest;
import com.company.networkmovers.modules.settings.dto.response.SettingsResponse;
import java.util.List;

public interface SettingsService {
    SettingsResponse create(SettingsRequest request);
    SettingsResponse findById(Long id);
    List<SettingsResponse> findAll();
    SettingsResponse update(Long id, SettingsRequest request);
    void delete(Long id);
}
