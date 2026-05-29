package com.company.networkmovers.modules.property.service.impl;

import com.company.networkmovers.modules.property.entity.MovePhase;
import com.company.networkmovers.modules.property.repository.MovePhaseRepository;
import com.company.networkmovers.modules.property.service.MovePhaseService;
import com.company.networkmovers.modules.property.dto.request.MovePhaseRequest;
import com.company.networkmovers.modules.property.dto.response.MovePhaseResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import com.company.networkmovers.shared.service.AbstractLookupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MovePhaseServiceImpl 
        extends AbstractLookupService<MovePhase, MovePhaseRequest, MovePhaseResponse, MovePhaseRepository> 
        implements MovePhaseService {

    public MovePhaseServiceImpl(MovePhaseRepository repository, 
                               GenericMapper<MovePhase, MovePhaseRequest, MovePhaseResponse> mapper) {
        super(repository, mapper);
    }

    @Override
    protected String getCodeFromRequest(MovePhaseRequest request) {
        return request.getCode();
    }

    @Override
    protected void updateEntityFields(MovePhase entity, MovePhaseRequest request) {
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setActive(request.isActive());
        entity.setSequenceNo(request.getSequenceNo());
    }
}
