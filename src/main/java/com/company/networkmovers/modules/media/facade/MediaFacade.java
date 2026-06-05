package com.company.networkmovers.modules.media.facade;

import java.util.UUID;

import com.company.networkmovers.modules.media.dto.request.MediaRequest;
import com.company.networkmovers.modules.media.dto.response.MediaResponse;
import com.company.networkmovers.modules.media.service.MediaService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class MediaFacade {

    private final MediaService service;

    public MediaFacade(MediaService service) {
        this.service = service;
    }

    public MediaResponse create(MediaRequest request) {
        return service.create(request);
    }

    public MediaResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<MediaResponse> findAll() {
        return service.findAll();
    }

    public MediaResponse update(UUID id, MediaRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
