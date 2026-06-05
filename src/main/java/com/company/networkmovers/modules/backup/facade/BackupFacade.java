package com.company.networkmovers.modules.backup.facade;

import java.util.UUID;

import com.company.networkmovers.modules.backup.dto.request.BackupRequest;
import com.company.networkmovers.modules.backup.dto.response.BackupResponse;
import com.company.networkmovers.modules.backup.service.BackupService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class BackupFacade {

    private final BackupService service;

    public BackupFacade(BackupService service) {
        this.service = service;
    }

    public BackupResponse create(BackupRequest request) {
        return service.create(request);
    }

    public BackupResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<BackupResponse> findAll() {
        return service.findAll();
    }

    public BackupResponse update(UUID id, BackupRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
