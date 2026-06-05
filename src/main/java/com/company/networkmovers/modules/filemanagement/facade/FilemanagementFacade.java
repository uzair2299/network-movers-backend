package com.company.networkmovers.modules.filemanagement.facade;

import java.util.UUID;

import com.company.networkmovers.modules.filemanagement.dto.request.FilemanagementRequest;
import com.company.networkmovers.modules.filemanagement.dto.response.FilemanagementResponse;
import com.company.networkmovers.modules.filemanagement.service.FilemanagementService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class FilemanagementFacade {

    private final FilemanagementService service;

    public FilemanagementFacade(FilemanagementService service) {
        this.service = service;
    }

    public FilemanagementResponse create(FilemanagementRequest request) {
        return service.create(request);
    }

    public FilemanagementResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<FilemanagementResponse> findAll() {
        return service.findAll();
    }

    public FilemanagementResponse update(UUID id, FilemanagementRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
