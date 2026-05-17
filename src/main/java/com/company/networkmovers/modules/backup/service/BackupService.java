package com.company.networkmovers.modules.backup.service;

import com.company.networkmovers.modules.backup.dto.request.BackupRequest;
import com.company.networkmovers.modules.backup.dto.response.BackupResponse;
import java.util.List;

public interface BackupService {
    BackupResponse create(BackupRequest request);
    BackupResponse findById(Long id);
    List<BackupResponse> findAll();
    BackupResponse update(Long id, BackupRequest request);
    void delete(Long id);
}
