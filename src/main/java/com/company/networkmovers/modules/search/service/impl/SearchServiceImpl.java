package com.company.networkmovers.modules.search.service.impl;

import java.util.UUID;

import com.company.networkmovers.modules.search.entity.SearchEntity;
import com.company.networkmovers.modules.search.repository.SearchRepository;
import com.company.networkmovers.modules.search.service.SearchService;
import com.company.networkmovers.modules.search.dto.request.SearchRequest;
import com.company.networkmovers.modules.search.dto.response.SearchResponse;
import com.company.networkmovers.modules.search.mapper.SearchMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SearchServiceImpl implements SearchService {

    private final SearchRepository repository;
    private final SearchMapper mapper;

    public SearchServiceImpl(SearchRepository repository, SearchMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public SearchResponse create(SearchRequest request) {
        SearchEntity entity = mapper.toEntity(request);
        SearchEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SearchResponse findById(UUID id) {
        SearchEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Search not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SearchResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SearchResponse update(UUID id, SearchRequest request) {
        SearchEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Search not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        SearchEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(UUID id) {
        SearchEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Search not found with id: " + id));
        repository.delete(entity);
    }
}
