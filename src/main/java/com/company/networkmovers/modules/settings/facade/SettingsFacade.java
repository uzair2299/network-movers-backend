package com.company.networkmovers.modules.settings.facade;

import java.util.UUID;

import com.company.networkmovers.modules.settings.dto.request.SettingsRequest;
import com.company.networkmovers.modules.settings.dto.response.SettingsResponse;
import com.company.networkmovers.modules.settings.service.SettingsService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class SettingsFacade {

    private final SettingsService service;

    public SettingsFacade(SettingsService service) {
        this.service = service;
    }

    public SettingsResponse create(SettingsRequest request) {
        return service.create(request);
    }

    public SettingsResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<SettingsResponse> findAll() {
        return service.findAll();
    }

    public SettingsResponse update(UUID id, SettingsRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
