package com.company.networkmovers.modules.search.facade;

import com.company.networkmovers.modules.search.dto.request.SearchRequest;
import com.company.networkmovers.modules.search.dto.response.SearchResponse;
import com.company.networkmovers.modules.search.service.SearchService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class SearchFacade {

    private final SearchService service;

    public SearchFacade(SearchService service) {
        this.service = service;
    }

    public SearchResponse create(SearchRequest request) {
        return service.create(request);
    }

    public SearchResponse findById(Long id) {
        return service.findById(id);
    }

    public List<SearchResponse> findAll() {
        return service.findAll();
    }

    public SearchResponse update(Long id, SearchRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
