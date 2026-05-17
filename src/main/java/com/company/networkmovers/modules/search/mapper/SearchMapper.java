package com.company.networkmovers.modules.search.mapper;

import com.company.networkmovers.modules.search.entity.SearchEntity;
import com.company.networkmovers.modules.search.dto.request.SearchRequest;
import com.company.networkmovers.modules.search.dto.response.SearchResponse;
import org.springframework.stereotype.Component;

@Component
public class SearchMapper {

    public SearchEntity toEntity(SearchRequest request) {
        if (request == null) return null;
        return SearchEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public SearchResponse toResponse(SearchEntity entity) {
        if (entity == null) return null;
        return SearchResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
