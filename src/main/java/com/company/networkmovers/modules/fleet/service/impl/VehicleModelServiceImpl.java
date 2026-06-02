package com.company.networkmovers.modules.fleet.service.impl;

import com.company.networkmovers.modules.fleet.dto.request.VehicleModelRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleModelResponse;
import com.company.networkmovers.modules.fleet.entity.VehicleMake;
import com.company.networkmovers.modules.fleet.entity.VehicleModel;
import com.company.networkmovers.modules.fleet.entity.VehicleType;
import com.company.networkmovers.modules.fleet.mapper.VehicleModelMapper;
import com.company.networkmovers.modules.fleet.repository.VehicleMakeRepository;
import com.company.networkmovers.modules.fleet.repository.VehicleModelRepository;
import com.company.networkmovers.modules.fleet.repository.VehicleTypeRepository;
import com.company.networkmovers.modules.fleet.service.VehicleModelService;
import com.company.networkmovers.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleModelServiceImpl implements VehicleModelService {

    private final VehicleModelRepository repository;
    private final VehicleModelMapper mapper;
    private final VehicleMakeRepository makeRepository;
    private final VehicleTypeRepository typeRepository;

    @Override
    @Transactional
    public VehicleModelResponse create(VehicleModelRequest request) {
        if (repository.existsByCode(request.getCode())) {
            throw new IllegalArgumentException("Vehicle model with code " + request.getCode() + " already exists.");
        }

        VehicleMake make = makeRepository.findById(request.getMakeId())
                .orElseThrow(() -> new ResourceNotFoundException("VehicleMake not found with id " + request.getMakeId()));
        VehicleType type = typeRepository.findById(request.getVehicleTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("VehicleType not found with id " + request.getVehicleTypeId()));

        VehicleModel entity = mapper.toEntity(request);
        entity.setMake(make);
        entity.setVehicleType(type);

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public VehicleModelResponse update(UUID id, VehicleModelRequest request) {
        VehicleModel entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VehicleModel not found with id " + id));

        if (repository.existsByCodeAndIdNot(request.getCode(), id)) {
            throw new IllegalArgumentException("Vehicle model with code " + request.getCode() + " already exists.");
        }

        VehicleMake make = makeRepository.findById(request.getMakeId())
                .orElseThrow(() -> new ResourceNotFoundException("VehicleMake not found with id " + request.getMakeId()));
        VehicleType type = typeRepository.findById(request.getVehicleTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("VehicleType not found with id " + request.getVehicleTypeId()));

        entity.setCode(request.getCode());
        entity.setName(request.getName());
        entity.setActive(request.isActive());
        entity.setCapacityKg(request.getCapacityKg());
        entity.setCapacityM3(request.getCapacityM3());
        entity.setLengthM(request.getLengthM());
        entity.setWidthM(request.getWidthM());
        entity.setHeightM(request.getHeightM());
        entity.setMake(make);
        entity.setVehicleType(type);

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public VehicleModelResponse getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("VehicleModel not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VehicleModelResponse> search(String query, Pageable pageable) {
        if (query == null || query.isBlank()) {
            return repository.findAll(pageable).map(mapper::toResponse);
        }
        return repository.findBySearch(query, pageable).map(mapper::toResponse);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        VehicleModel entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VehicleModel not found with id " + id));
        repository.delete(entity);
    }
}
