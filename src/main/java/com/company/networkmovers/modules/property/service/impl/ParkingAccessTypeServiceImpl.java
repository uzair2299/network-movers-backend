package com.company.networkmovers.modules.property.service.impl;

import com.company.networkmovers.modules.property.entity.ParkingAccessType;
import com.company.networkmovers.modules.property.repository.ParkingAccessTypeRepository;
import com.company.networkmovers.modules.property.service.ParkingAccessTypeService;
import com.company.networkmovers.shared.dto.LookupRequest;
import com.company.networkmovers.shared.dto.LookupResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import com.company.networkmovers.shared.service.AbstractLookupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ParkingAccessTypeServiceImpl 
        extends AbstractLookupService<ParkingAccessType, LookupRequest, LookupResponse, ParkingAccessTypeRepository> 
        implements ParkingAccessTypeService {

    public ParkingAccessTypeServiceImpl(ParkingAccessTypeRepository repository, 
                                        GenericMapper<ParkingAccessType, LookupRequest, LookupResponse> mapper) {
        super(repository, mapper);
    }

    @Override
    protected String getCodeFromRequest(LookupRequest request) {
        return request.getCode();
    }

    @Override
    protected void updateEntityFields(ParkingAccessType entity, LookupRequest request) {
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setActive(request.isActive());
    }
}
