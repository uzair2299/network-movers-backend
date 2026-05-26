package com.company.networkmovers.shared.service;

import java.util.List;
import java.util.UUID;

public interface GenericLookupService<REQ, RES> {
    RES create(REQ request);
    RES update(UUID id, REQ request);
    RES getById(UUID id);
    List<RES> getAllActive();
    void delete(UUID id);
}
