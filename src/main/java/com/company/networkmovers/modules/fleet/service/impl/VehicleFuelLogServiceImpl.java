package com.company.networkmovers.modules.fleet.service.impl;

import com.company.networkmovers.modules.fleet.dto.request.VehicleFuelLogRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleFuelLogResponse;
import com.company.networkmovers.modules.fleet.entity.Vehicle;
import com.company.networkmovers.modules.fleet.entity.VehicleFuelLog;
import com.company.networkmovers.modules.fleet.mapper.VehicleFuelLogMapper;
import com.company.networkmovers.modules.fleet.repository.VehicleFuelLogRepository;
import com.company.networkmovers.modules.fleet.repository.VehicleRepository;
import com.company.networkmovers.modules.fleet.service.VehicleFuelLogService;
import com.company.networkmovers.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleFuelLogServiceImpl implements VehicleFuelLogService {

    private final VehicleFuelLogRepository repository;
    private final VehicleFuelLogMapper mapper;
    private final VehicleRepository vehicleRepository;

    @Override
    @Transactional
    public VehicleFuelLogResponse create(VehicleFuelLogRequest request) {
        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id " + request.getVehicleId()));

        VehicleFuelLog entity = mapper.toEntity(request);
        entity.setVehicle(vehicle);

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public VehicleFuelLogResponse update(UUID id, VehicleFuelLogRequest request) {
        VehicleFuelLog entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VehicleFuelLog not found with id " + id));

        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id " + request.getVehicleId()));

        entity.setFuelDate(request.getFuelDate());
        entity.setFuelQuantityLiters(request.getFuelQuantityLiters());
        entity.setCostAmount(request.getCostAmount());
        entity.setOdometerKm(request.getOdometerKm());
        entity.setFuelStation(request.getFuelStation());
        entity.setRemarks(request.getRemarks());
        
        entity.setVehicle(vehicle);

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public VehicleFuelLogResponse getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("VehicleFuelLog not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VehicleFuelLogResponse> getByVehicleId(UUID vehicleId, Pageable pageable) {
        return repository.findByVehicleId(vehicleId, pageable).map(mapper::toResponse);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        VehicleFuelLog entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VehicleFuelLog not found with id " + id));
        repository.delete(entity);
    }
}
