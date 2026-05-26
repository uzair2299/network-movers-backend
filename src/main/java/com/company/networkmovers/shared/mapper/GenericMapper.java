package com.company.networkmovers.shared.mapper;

public interface GenericMapper<E, REQ, RES> {
    E toEntity(REQ request);
    RES toResponse(E entity);
}
