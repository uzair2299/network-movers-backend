package com.company.networkmovers.modules.movingitem.service;

import com.company.networkmovers.modules.movingitem.dto.request.MovingitemRequest;
import com.company.networkmovers.modules.movingitem.dto.response.MovingitemResponse;
import java.util.List;

public interface MovingitemService {
    MovingitemResponse create(MovingitemRequest request);
    MovingitemResponse findById(Long id);
    List<MovingitemResponse> findAll();
    MovingitemResponse update(Long id, MovingitemRequest request);
    void delete(Long id);
}
