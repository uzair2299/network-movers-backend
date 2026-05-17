package com.company.networkmovers.modules.configuration.service;

import com.company.networkmovers.modules.configuration.dto.request.ConfigurationRequest;
import com.company.networkmovers.modules.configuration.dto.response.ConfigurationResponse;
import java.util.List;

public interface ConfigurationService {
    ConfigurationResponse create(ConfigurationRequest request);
    ConfigurationResponse findById(Long id);
    List<ConfigurationResponse> findAll();
    ConfigurationResponse update(Long id, ConfigurationRequest request);
    void delete(Long id);
}
