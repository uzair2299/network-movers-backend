package com.company.networkmovers.modules.search.service;

import java.util.UUID;

import com.company.networkmovers.modules.search.dto.request.SearchRequest;
import com.company.networkmovers.modules.search.dto.response.SearchResponse;
import java.util.List;

public interface SearchService {
    SearchResponse create(SearchRequest request);
    SearchResponse findById(UUID id);
    List<SearchResponse> findAll();
    SearchResponse update(UUID id, SearchRequest request);
    void delete(UUID id);
}
