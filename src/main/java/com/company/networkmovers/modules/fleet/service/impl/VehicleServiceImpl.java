package com.company.networkmovers.modules.fleet.service.impl;

import com.company.networkmovers.modules.fleet.dto.request.VehicleRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleResponse;
import com.company.networkmovers.modules.fleet.entity.Vehicle;
import com.company.networkmovers.modules.fleet.entity.VehicleModel;
import com.company.networkmovers.modules.fleet.entity.enums.VehicleStatus;
import com.company.networkmovers.modules.fleet.mapper.VehicleMapper;
import com.company.networkmovers.modules.fleet.repository.VehicleModelRepository;
import com.company.networkmovers.modules.fleet.repository.VehicleRepository;
import com.company.networkmovers.modules.fleet.service.VehicleService;
import com.company.networkmovers.shared.dto.RequestParamDto;
import com.company.networkmovers.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("fleetVehicleServiceImpl")
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository repository;
    private final VehicleMapper mapper;
    private final VehicleModelRepository modelRepository;

    @Override
    @Transactional
    public VehicleResponse create(VehicleRequest request) {
        if (repository.existsByVehicleCode(request.getVehicleCode())) {
            throw new IllegalArgumentException("Vehicle with code " + request.getVehicleCode() + " already exists.");
        }
        if (repository.existsByRegistrationNo(request.getRegistrationNo())) {
            throw new IllegalArgumentException("Vehicle with registration number " + request.getRegistrationNo() + " already exists.");
        }

        VehicleModel model = modelRepository.findById(request.getVehicleModelId())
                .orElseThrow(() -> new ResourceNotFoundException("VehicleModel not found with id " + request.getVehicleModelId()));

        Vehicle entity = mapper.toEntity(request);
        entity.setVehicleModel(model);

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public VehicleResponse update(UUID id, VehicleRequest request) {
        Vehicle entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id " + id));

        if (repository.existsByVehicleCodeAndIdNot(request.getVehicleCode(), id)) {
            throw new IllegalArgumentException("Vehicle with code " + request.getVehicleCode() + " already exists.");
        }
        if (repository.existsByRegistrationNoAndIdNot(request.getRegistrationNo(), id)) {
            throw new IllegalArgumentException("Vehicle with registration number " + request.getRegistrationNo() + " already exists.");
        }

        VehicleModel model = modelRepository.findById(request.getVehicleModelId())
                .orElseThrow(() -> new ResourceNotFoundException("VehicleModel not found with id " + request.getVehicleModelId()));

        entity.setVehicleCode(request.getVehicleCode());
        entity.setRegistrationNo(request.getRegistrationNo());
        entity.setManufactureYear(request.getManufactureYear());
        entity.setOwnershipType(request.getOwnershipType());
        entity.setStatus(request.getStatus());
        entity.setCurrentOdometerKm(request.getCurrentOdometerKm());
        entity.setInsuranceExpiryDate(request.getInsuranceExpiryDate());
        entity.setFitnessExpiryDate(request.getFitnessExpiryDate());
        entity.setAcquisitionDate(request.getAcquisitionDate());
        entity.setActive(request.isActive());
        entity.setRemarks(request.getRemarks());
        entity.setVehicleModel(model);

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public VehicleResponse updateStatus(UUID id, VehicleStatus status) {
        Vehicle entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id " + id));
        entity.setStatus(status);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public VehicleResponse getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleResponse> getAllActive() {
        return repository.findAll().stream()
                .filter(Vehicle::isActive)
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VehicleResponse> getAll(RequestParamDto requestParams) {
        String[] sortParams = requestParams.getSort().split(",");
        String sortField = sortParams[0];
        Sort.Direction direction = Sort.Direction.ASC;
        if (sortParams.length > 1 && "desc".equalsIgnoreCase(sortParams[1])) {
            direction = Sort.Direction.DESC;
        }
        Pageable pageable = PageRequest.of(
                requestParams.getPage(),
                requestParams.getSize(),
                Sort.by(direction, sortField)
        );
        String search = requestParams.getSearch();
        if (search == null || search.isBlank()) {
            return repository.findAll(pageable).map(mapper::toResponse);
        }
        return repository.findBySearch(search, pageable).map(mapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VehicleResponse> searchByStatus(VehicleStatus status, Pageable pageable) {
        return repository.findByStatus(status, pageable).map(mapper::toResponse);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Vehicle entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id " + id));
        // Consistent with AbstractLookupService, set active = false for delete
        entity.setActive(false);
        repository.save(entity);
    }
}
