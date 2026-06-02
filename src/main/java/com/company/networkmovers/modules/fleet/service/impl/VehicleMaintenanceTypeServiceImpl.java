package com.company.networkmovers.modules.fleet.service.impl;

import com.company.networkmovers.modules.fleet.dto.request.VehicleMaintenanceTypeRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleMaintenanceTypeResponse;
import com.company.networkmovers.modules.fleet.entity.VehicleMaintenanceType;
import com.company.networkmovers.modules.fleet.mapper.VehicleMaintenanceTypeMapper;
import com.company.networkmovers.modules.fleet.repository.VehicleMaintenanceTypeRepository;
import com.company.networkmovers.modules.fleet.service.VehicleMaintenanceTypeService;
import com.company.networkmovers.shared.service.AbstractLookupService;
import org.springframework.stereotype.Service;

@Service
public class VehicleMaintenanceTypeServiceImpl 
    extends AbstractLookupService<VehicleMaintenanceType, VehicleMaintenanceTypeRequest, VehicleMaintenanceTypeResponse, VehicleMaintenanceTypeRepository> 
    implements VehicleMaintenanceTypeService {

    public VehicleMaintenanceTypeServiceImpl(VehicleMaintenanceTypeRepository repository, VehicleMaintenanceTypeMapper mapper) {
        super(repository, mapper);
    }
@Override
    protected String getCodeFromRequest(com.company.networkmovers.modules.fleet.dto.request.VehicleMaintenanceTypeRequest request) {
        return request.getCode();
    }

    @Override
    protected void updateEntityFields(VehicleMaintenanceType entity, VehicleMaintenanceTypeRequest request) {
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setActive(request.isActive());
        entity.setDescription(request.getDescription());
    }
}
