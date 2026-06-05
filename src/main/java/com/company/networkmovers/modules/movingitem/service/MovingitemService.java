package com.company.networkmovers.modules.movingitem.service;

import java.util.UUID;

import com.company.networkmovers.modules.movingitem.dto.request.MovingitemRequest;
import com.company.networkmovers.modules.movingitem.dto.response.MovingitemResponse;
import java.util.List;

public interface MovingitemService {
    MovingitemResponse create(MovingitemRequest request);
    MovingitemResponse findById(UUID id);
    List<MovingitemResponse> findAll();
    MovingitemResponse update(UUID id, MovingitemRequest request);
    void delete(UUID id);
}
