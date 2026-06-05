package com.company.networkmovers.modules.settings.service;

import java.util.UUID;

import com.company.networkmovers.modules.settings.dto.request.SettingsRequest;
import com.company.networkmovers.modules.settings.dto.response.SettingsResponse;
import java.util.List;

public interface SettingsService {
    SettingsResponse create(SettingsRequest request);
    SettingsResponse findById(UUID id);
    List<SettingsResponse> findAll();
    SettingsResponse update(UUID id, SettingsRequest request);
    void delete(UUID id);
}
