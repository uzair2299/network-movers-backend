package com.company.networkmovers.modules.document.service.impl;

import com.company.networkmovers.modules.document.entity.DocumentEntity;
import com.company.networkmovers.modules.document.repository.DocumentRepository;
import com.company.networkmovers.modules.document.service.DocumentService;
import com.company.networkmovers.modules.document.dto.request.DocumentRequest;
import com.company.networkmovers.modules.document.dto.response.DocumentResponse;
import com.company.networkmovers.modules.document.mapper.DocumentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository repository;
    private final DocumentMapper mapper;

    public DocumentServiceImpl(DocumentRepository repository, DocumentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public DocumentResponse create(DocumentRequest request) {
        DocumentEntity entity = mapper.toEntity(request);
        DocumentEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentResponse findById(Long id) {
        DocumentEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DocumentResponse update(Long id, DocumentRequest request) {
        DocumentEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        DocumentEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        DocumentEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found with id: " + id));
        repository.delete(entity);
    }
}
