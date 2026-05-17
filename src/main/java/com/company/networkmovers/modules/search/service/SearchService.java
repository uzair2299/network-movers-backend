package com.company.networkmovers.modules.search.service;

import com.company.networkmovers.modules.search.dto.request.SearchRequest;
import com.company.networkmovers.modules.search.dto.response.SearchResponse;
import java.util.List;

public interface SearchService {
    SearchResponse create(SearchRequest request);
    SearchResponse findById(Long id);
    List<SearchResponse> findAll();
    SearchResponse update(Long id, SearchRequest request);
    void delete(Long id);
}
