package com.company.networkmovers.modules.filemanagement.service;

import java.util.UUID;

import com.company.networkmovers.modules.filemanagement.dto.request.FilemanagementRequest;
import com.company.networkmovers.modules.filemanagement.dto.response.FilemanagementResponse;
import java.util.List;

public interface FilemanagementService {
    FilemanagementResponse create(FilemanagementRequest request);
    FilemanagementResponse findById(UUID id);
    List<FilemanagementResponse> findAll();
    FilemanagementResponse update(UUID id, FilemanagementRequest request);
    void delete(UUID id);
}
