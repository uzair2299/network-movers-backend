package com.company.networkmovers.modules.filemanagement.facade;

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

    public FilemanagementResponse findById(Long id) {
        return service.findById(id);
    }

    public List<FilemanagementResponse> findAll() {
        return service.findAll();
    }

    public FilemanagementResponse update(Long id, FilemanagementRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
