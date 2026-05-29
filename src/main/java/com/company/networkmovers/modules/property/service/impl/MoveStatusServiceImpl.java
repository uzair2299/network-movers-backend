package com.company.networkmovers.modules.property.service.impl;

import com.company.networkmovers.modules.property.entity.MoveStatus;
import com.company.networkmovers.modules.property.entity.MovePhase;
import com.company.networkmovers.modules.property.repository.MoveStatusRepository;
import com.company.networkmovers.modules.property.repository.MovePhaseRepository;
import com.company.networkmovers.modules.property.service.MoveStatusService;
import com.company.networkmovers.modules.property.dto.request.MoveStatusRequest;
import com.company.networkmovers.modules.property.dto.response.MoveStatusResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import com.company.networkmovers.shared.service.AbstractLookupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MoveStatusServiceImpl 
        extends AbstractLookupService<MoveStatus, MoveStatusRequest, MoveStatusResponse, MoveStatusRepository> 
        implements MoveStatusService {

    private final MovePhaseRepository phaseRepository;

    public MoveStatusServiceImpl(MoveStatusRepository repository, 
                                 GenericMapper<MoveStatus, MoveStatusRequest, MoveStatusResponse> mapper,
                                 MovePhaseRepository phaseRepository) {
        super(repository, mapper);
        this.phaseRepository = phaseRepository;
    }

    @Override
    @Transactional
    public MoveStatusResponse create(MoveStatusRequest request) {
        if (request.getPhaseId() == null) {
            throw new RuntimeException("MovePhase ID is required");
        }
        MovePhase phase = phaseRepository.findById(request.getPhaseId())
                .orElseThrow(() -> new RuntimeException("MovePhase not found with ID: " + request.getPhaseId()));

        if (request.getCode() != null && repository.existsByCode(request.getCode())) {
            throw new RuntimeException("Code already exists: " + request.getCode());
        }

        MoveStatus entity = mapper.toEntity(request);
        entity.setPhase(phase);

        MoveStatus saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    protected String getCodeFromRequest(MoveStatusRequest request) {
        return request.getCode();
    }

    @Override
    protected void updateEntityFields(MoveStatus entity, MoveStatusRequest request) {
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setActive(request.isActive());
        entity.setDescription(request.getDescription());
        entity.setSequenceNo(request.getSequenceNo());
        entity.setFinal(request.isFinal());
        entity.setColorCode(request.getColorCode());
        entity.setCustomerVisible(request.isCustomerVisible());
        entity.setInternalOnly(request.isInternalOnly());
        
        if (request.getPhaseId() != null) {
            MovePhase phase = phaseRepository.findById(request.getPhaseId())
                    .orElseThrow(() -> new RuntimeException("MovePhase not found with ID: " + request.getPhaseId()));
            entity.setPhase(phase);
        }
    }
}
