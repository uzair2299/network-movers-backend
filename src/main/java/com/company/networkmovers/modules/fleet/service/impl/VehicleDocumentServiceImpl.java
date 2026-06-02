package com.company.networkmovers.modules.fleet.service.impl;

import com.company.networkmovers.modules.document.entity.DocumentType;
import com.company.networkmovers.modules.document.repository.DocumentTypeRepository;
import com.company.networkmovers.modules.fleet.dto.request.VehicleDocumentRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleDocumentResponse;
import com.company.networkmovers.modules.fleet.entity.Vehicle;
import com.company.networkmovers.modules.fleet.entity.VehicleDocument;
import com.company.networkmovers.modules.fleet.mapper.VehicleDocumentMapper;
import com.company.networkmovers.modules.fleet.repository.VehicleDocumentRepository;
import com.company.networkmovers.modules.fleet.repository.VehicleRepository;
import com.company.networkmovers.modules.fleet.service.VehicleDocumentService;
import com.company.networkmovers.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleDocumentServiceImpl implements VehicleDocumentService {

    private final VehicleDocumentRepository repository;
    private final VehicleDocumentMapper mapper;
    private final VehicleRepository vehicleRepository;
    private final DocumentTypeRepository documentTypeRepository;

    @Override
    @Transactional
    public VehicleDocumentResponse create(VehicleDocumentRequest request) {
        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id " + request.getVehicleId()));
        DocumentType docType = documentTypeRepository.findById(request.getDocumentTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("DocumentType not found with id " + request.getDocumentTypeId()));

        VehicleDocument entity = mapper.toEntity(request);
        entity.setVehicle(vehicle);
        entity.setDocumentType(docType);

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public VehicleDocumentResponse update(UUID id, VehicleDocumentRequest request) {
        VehicleDocument entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VehicleDocument not found with id " + id));

        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id " + request.getVehicleId()));
        DocumentType docType = documentTypeRepository.findById(request.getDocumentTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("DocumentType not found with id " + request.getDocumentTypeId()));

        entity.setDocumentNumber(request.getDocumentNumber());
        entity.setIssueDate(request.getIssueDate());
        entity.setExpiryDate(request.getExpiryDate());
        entity.setFileUrl(request.getFileUrl());
        entity.setVerified(request.isVerified());
        entity.setRemarks(request.getRemarks());
        
        entity.setVehicle(vehicle);
        entity.setDocumentType(docType);

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public VehicleDocumentResponse getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("VehicleDocument not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VehicleDocumentResponse> getByVehicleId(UUID vehicleId, Pageable pageable) {
        return repository.findByVehicleId(vehicleId, pageable).map(mapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VehicleDocumentResponse> getExpiringDocuments(int daysAhead, Pageable pageable) {
        LocalDate expiryTarget = LocalDate.now().plusDays(daysAhead);
        return repository.findExpiringDocuments(expiryTarget, pageable).map(mapper::toResponse);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        VehicleDocument entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VehicleDocument not found with id " + id));
        repository.delete(entity);
    }
}
