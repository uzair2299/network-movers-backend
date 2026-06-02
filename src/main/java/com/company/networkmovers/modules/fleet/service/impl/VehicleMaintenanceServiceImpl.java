package com.company.networkmovers.modules.fleet.service.impl;

import com.company.networkmovers.modules.fleet.dto.request.VehicleMaintenanceRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleMaintenanceResponse;
import com.company.networkmovers.modules.fleet.entity.Vehicle;
import com.company.networkmovers.modules.fleet.entity.VehicleMaintenance;
import com.company.networkmovers.modules.fleet.entity.VehicleMaintenanceType;
import com.company.networkmovers.modules.fleet.mapper.VehicleMaintenanceMapper;
import com.company.networkmovers.modules.fleet.repository.VehicleMaintenanceRepository;
import com.company.networkmovers.modules.fleet.repository.VehicleMaintenanceTypeRepository;
import com.company.networkmovers.modules.fleet.repository.VehicleRepository;
import com.company.networkmovers.modules.fleet.service.VehicleMaintenanceService;
import com.company.networkmovers.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleMaintenanceServiceImpl implements VehicleMaintenanceService {

    private final VehicleMaintenanceRepository repository;
    private final VehicleMaintenanceMapper mapper;
    private final VehicleRepository vehicleRepository;
    private final VehicleMaintenanceTypeRepository maintenanceTypeRepository;

    @Override
    @Transactional
    public VehicleMaintenanceResponse create(VehicleMaintenanceRequest request) {
        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id " + request.getVehicleId()));
        VehicleMaintenanceType type = maintenanceTypeRepository.findById(request.getMaintenanceTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("VehicleMaintenanceType not found with id " + request.getMaintenanceTypeId()));

        VehicleMaintenance entity = mapper.toEntity(request);
        entity.setVehicle(vehicle);
        entity.setMaintenanceType(type);

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public VehicleMaintenanceResponse update(UUID id, VehicleMaintenanceRequest request) {
        VehicleMaintenance entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VehicleMaintenance not found with id " + id));

        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id " + request.getVehicleId()));
        VehicleMaintenanceType type = maintenanceTypeRepository.findById(request.getMaintenanceTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("VehicleMaintenanceType not found with id " + request.getMaintenanceTypeId()));

        entity.setMaintenanceDate(request.getMaintenanceDate());
        entity.setOdometerKm(request.getOdometerKm());
        entity.setCost(request.getCost());
        entity.setVendorName(request.getVendorName());
        entity.setNextServiceDate(request.getNextServiceDate());
        entity.setNextServiceKm(request.getNextServiceKm());
        entity.setRemarks(request.getRemarks());
        
        entity.setVehicle(vehicle);
        entity.setMaintenanceType(type);

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public VehicleMaintenanceResponse getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("VehicleMaintenance not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VehicleMaintenanceResponse> getByVehicleId(UUID vehicleId, Pageable pageable) {
        return repository.findByVehicleId(vehicleId, pageable).map(mapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VehicleMaintenanceResponse> getUpcomingMaintenance(int daysAhead, Pageable pageable) {
        LocalDate targetDate = LocalDate.now().plusDays(daysAhead);
        BigDecimal targetKm = BigDecimal.valueOf(1000000); // Generic large value if needed, although JPQL OR clause handles it
        return repository.findUpcomingMaintenance(targetDate, targetKm, pageable).map(mapper::toResponse);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        VehicleMaintenance entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VehicleMaintenance not found with id " + id));
        repository.delete(entity);
    }
}
