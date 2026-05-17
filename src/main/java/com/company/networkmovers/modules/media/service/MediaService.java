package com.company.networkmovers.modules.media.service;

import com.company.networkmovers.modules.media.dto.request.MediaRequest;
import com.company.networkmovers.modules.media.dto.response.MediaResponse;
import java.util.List;

public interface MediaService {
    MediaResponse create(MediaRequest request);
    MediaResponse findById(Long id);
    List<MediaResponse> findAll();
    MediaResponse update(Long id, MediaRequest request);
    void delete(Long id);
}
