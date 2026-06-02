package com.company.networkmovers.modules.fleet.service.impl;

import com.company.networkmovers.modules.fleet.dto.request.VehicleMakeRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleMakeResponse;
import com.company.networkmovers.modules.fleet.entity.VehicleMake;
import com.company.networkmovers.modules.fleet.mapper.VehicleMakeMapper;
import com.company.networkmovers.modules.fleet.repository.VehicleMakeRepository;
import com.company.networkmovers.modules.fleet.service.VehicleMakeService;
import com.company.networkmovers.shared.service.AbstractLookupService;
import org.springframework.stereotype.Service;

@Service
public class VehicleMakeServiceImpl 
    extends AbstractLookupService<VehicleMake, VehicleMakeRequest, VehicleMakeResponse, VehicleMakeRepository> 
    implements VehicleMakeService {

    public VehicleMakeServiceImpl(VehicleMakeRepository repository, VehicleMakeMapper mapper) {
        super(repository, mapper);
    }
@Override
    protected String getCodeFromRequest(com.company.networkmovers.modules.fleet.dto.request.VehicleMakeRequest request) {
        return request.getCode();
    }

    @Override
    protected void updateEntityFields(VehicleMake entity, VehicleMakeRequest request) {
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setActive(request.isActive());
        entity.setCountry(request.getCountry());
    }
}
