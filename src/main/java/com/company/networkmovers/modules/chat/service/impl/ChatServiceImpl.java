package com.company.networkmovers.modules.chat.service.impl;

import com.company.networkmovers.modules.chat.entity.ChatEntity;
import com.company.networkmovers.modules.chat.repository.ChatRepository;
import com.company.networkmovers.modules.chat.service.ChatService;
import com.company.networkmovers.modules.chat.dto.request.ChatRequest;
import com.company.networkmovers.modules.chat.dto.response.ChatResponse;
import com.company.networkmovers.modules.chat.mapper.ChatMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ChatServiceImpl implements ChatService {

    private final ChatRepository repository;
    private final ChatMapper mapper;

    public ChatServiceImpl(ChatRepository repository, ChatMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ChatResponse create(ChatRequest request) {
        ChatEntity entity = mapper.toEntity(request);
        ChatEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ChatResponse findById(Long id) {
        ChatEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chat not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ChatResponse update(Long id, ChatRequest request) {
        ChatEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chat not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        ChatEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        ChatEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chat not found with id: " + id));
        repository.delete(entity);
    }
}
