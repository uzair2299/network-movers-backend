package com.company.networkmovers.modules.media.service;

import java.util.UUID;

import com.company.networkmovers.modules.media.dto.request.MediaRequest;
import com.company.networkmovers.modules.media.dto.response.MediaResponse;
import java.util.List;

public interface MediaService {
    MediaResponse create(MediaRequest request);
    MediaResponse findById(UUID id);
    List<MediaResponse> findAll();
    MediaResponse update(UUID id, MediaRequest request);
    void delete(UUID id);
}
