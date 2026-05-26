package com.company.networkmovers.shared.service;

import com.company.networkmovers.shared.dto.RequestParamDto;
import com.company.networkmovers.shared.entity.BaseLookupEntity;
import com.company.networkmovers.shared.mapper.GenericMapper;
import com.company.networkmovers.shared.repository.BaseLookupRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class AbstractLookupService<E extends BaseLookupEntity, REQ, RES, REP extends BaseLookupRepository<E>> 
        implements GenericLookupService<REQ, RES> {

    protected final REP repository;
    protected final GenericMapper<E, REQ, RES> mapper;

    protected AbstractLookupService(REP repository, GenericMapper<E, REQ, RES> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public RES create(REQ request) {
        String code = getCodeFromRequest(request);
        if (code != null && repository.existsByCode(code)) {
            throw new RuntimeException("Code already exists: " + code);
        }
        E entity = mapper.toEntity(request);
        E saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public RES update(UUID id, REQ request) {
        E entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found with id: " + id));
        String code = getCodeFromRequest(request);
        if (code != null && repository.existsByCodeAndIdNot(code, id)) {
            throw new RuntimeException("Code already exists for another record: " + code);
        }
        updateEntityFields(entity, request);
        E updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public RES getById(UUID id) {
        E entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RES> getAllActive() {
        return repository.findAllActive().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        E entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found with id: " + id));
        entity.setActive(false);
        repository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RES> getAll(RequestParamDto requestParams) {
        Pageable pageable = createPageable(requestParams);
        String search = requestParams.getSearch();
        Page<E> entityPage;
        if (search == null || search.trim().isEmpty()) {
            entityPage = repository.findAll(pageable);
        } else {
            entityPage = repository.findBySearch(search.trim(), pageable);
        }
        return entityPage.map(mapper::toResponse);
    }

    protected Pageable createPageable(RequestParamDto requestParams) {
        String[] sortParams = requestParams.getSort().split(",");
        String sortField = sortParams[0];
        Sort.Direction direction = Sort.Direction.ASC;
        if (sortParams.length > 1 && "desc".equalsIgnoreCase(sortParams[1])) {
            direction = Sort.Direction.DESC;
        }
        return PageRequest.of(
                requestParams.getPage(),
                requestParams.getSize(),
                Sort.by(direction, sortField)
        );
    }

    protected abstract String getCodeFromRequest(REQ request);
    protected abstract void updateEntityFields(E entity, REQ request);
}
