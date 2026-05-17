package com.company.networkmovers.modules.filemanagement.service;

import com.company.networkmovers.modules.filemanagement.dto.request.FilemanagementRequest;
import com.company.networkmovers.modules.filemanagement.dto.response.FilemanagementResponse;
import java.util.List;

public interface FilemanagementService {
    FilemanagementResponse create(FilemanagementRequest request);
    FilemanagementResponse findById(Long id);
    List<FilemanagementResponse> findAll();
    FilemanagementResponse update(Long id, FilemanagementRequest request);
    void delete(Long id);
}
