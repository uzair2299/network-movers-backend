package com.company.networkmovers.modules.backup.service;

import java.util.UUID;

import com.company.networkmovers.modules.backup.dto.request.BackupRequest;
import com.company.networkmovers.modules.backup.dto.response.BackupResponse;
import java.util.List;

public interface BackupService {
    BackupResponse create(BackupRequest request);
    BackupResponse findById(UUID id);
    List<BackupResponse> findAll();
    BackupResponse update(UUID id, BackupRequest request);
    void delete(UUID id);
}
