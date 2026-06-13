package com.company.networkmovers.modules.asset.service.impl;

import com.company.networkmovers.modules.asset.entity.UnitOfMeasure;
import com.company.networkmovers.modules.asset.dto.request.UnitOfMeasureRequest;
import com.company.networkmovers.modules.asset.dto.response.UnitOfMeasureResponse;
import com.company.networkmovers.modules.asset.mapper.UnitOfMeasureMapper;
import com.company.networkmovers.modules.asset.repository.UnitOfMeasureRepository;
import com.company.networkmovers.modules.asset.service.UnitOfMeasureService;
import com.company.networkmovers.shared.service.AbstractLookupService;
import org.springframework.stereotype.Service;

@Service
public class UnitOfMeasureServiceImpl 
    extends AbstractLookupService<UnitOfMeasure, UnitOfMeasureRequest, UnitOfMeasureResponse, UnitOfMeasureRepository>
    implements UnitOfMeasureService {

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository repository, UnitOfMeasureMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected String getCodeFromRequest(UnitOfMeasureRequest request) {
        return request.getCode();
    }

    @Override
    protected void updateEntityFields(UnitOfMeasure entity, UnitOfMeasureRequest request) {
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setActive(request.isActive());
    }
}
