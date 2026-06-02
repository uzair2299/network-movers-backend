package com.company.networkmovers.modules.fleet.service.impl;

import com.company.networkmovers.modules.fleet.dto.request.VehicleTypeRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleTypeResponse;
import com.company.networkmovers.modules.fleet.entity.VehicleType;
import com.company.networkmovers.modules.fleet.mapper.VehicleTypeMapper;
import com.company.networkmovers.modules.fleet.repository.VehicleTypeRepository;
import com.company.networkmovers.modules.fleet.service.VehicleTypeService;
import com.company.networkmovers.shared.service.AbstractLookupService;
import org.springframework.stereotype.Service;

@Service
public class VehicleTypeServiceImpl 
    extends AbstractLookupService<VehicleType, VehicleTypeRequest, VehicleTypeResponse, VehicleTypeRepository> 
    implements VehicleTypeService {

    public VehicleTypeServiceImpl(VehicleTypeRepository repository, VehicleTypeMapper mapper) {
        super(repository, mapper);
    }
@Override
    protected String getCodeFromRequest(com.company.networkmovers.modules.fleet.dto.request.VehicleTypeRequest request) {
        return request.getCode();
    }

    @Override
    protected void updateEntityFields(VehicleType entity, VehicleTypeRequest request) {
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setActive(request.isActive());
        entity.setDescription(request.getDescription());
    }
}
