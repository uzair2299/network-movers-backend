package com.company.networkmovers.modules.configuration.facade;

import java.util.UUID;

import com.company.networkmovers.modules.configuration.dto.request.ConfigurationRequest;
import com.company.networkmovers.modules.configuration.dto.response.ConfigurationResponse;
import com.company.networkmovers.modules.configuration.service.ConfigurationService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ConfigurationFacade {

    private final ConfigurationService service;

    public ConfigurationFacade(ConfigurationService service) {
        this.service = service;
    }

    public ConfigurationResponse create(ConfigurationRequest request) {
        return service.create(request);
    }

    public ConfigurationResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<ConfigurationResponse> findAll() {
        return service.findAll();
    }

    public ConfigurationResponse update(UUID id, ConfigurationRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
