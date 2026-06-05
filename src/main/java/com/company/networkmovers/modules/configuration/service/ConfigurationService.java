package com.company.networkmovers.modules.configuration.service;

import java.util.UUID;

import com.company.networkmovers.modules.configuration.dto.request.ConfigurationRequest;
import com.company.networkmovers.modules.configuration.dto.response.ConfigurationResponse;
import java.util.List;

public interface ConfigurationService {
    ConfigurationResponse create(ConfigurationRequest request);
    ConfigurationResponse findById(UUID id);
    List<ConfigurationResponse> findAll();
    ConfigurationResponse update(UUID id, ConfigurationRequest request);
    void delete(UUID id);
}
