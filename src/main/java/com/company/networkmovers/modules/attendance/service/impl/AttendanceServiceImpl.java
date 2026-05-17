package com.company.networkmovers.modules.attendance.service.impl;

import com.company.networkmovers.modules.attendance.entity.AttendanceEntity;
import com.company.networkmovers.modules.attendance.repository.AttendanceRepository;
import com.company.networkmovers.modules.attendance.service.AttendanceService;
import com.company.networkmovers.modules.attendance.dto.request.AttendanceRequest;
import com.company.networkmovers.modules.attendance.dto.response.AttendanceResponse;
import com.company.networkmovers.modules.attendance.mapper.AttendanceMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository repository;
    private final AttendanceMapper mapper;

    public AttendanceServiceImpl(AttendanceRepository repository, AttendanceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AttendanceResponse create(AttendanceRequest request) {
        AttendanceEntity entity = mapper.toEntity(request);
        AttendanceEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public AttendanceResponse findById(Long id) {
        AttendanceEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AttendanceResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AttendanceResponse update(Long id, AttendanceRequest request) {
        AttendanceEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        AttendanceEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        AttendanceEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + id));
        repository.delete(entity);
    }
}
