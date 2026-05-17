package com.company.networkmovers.modules.search.validator;

import com.company.networkmovers.modules.search.dto.request.SearchRequest;
import org.springframework.stereotype.Component;

@Component
public class SearchValidator {

    public void validate(SearchRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
