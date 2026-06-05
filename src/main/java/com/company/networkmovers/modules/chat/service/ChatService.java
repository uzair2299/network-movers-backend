package com.company.networkmovers.modules.chat.service;

import java.util.UUID;

import com.company.networkmovers.modules.chat.dto.request.ChatRequest;
import com.company.networkmovers.modules.chat.dto.response.ChatResponse;
import java.util.List;

public interface ChatService {
    ChatResponse create(ChatRequest request);
    ChatResponse findById(UUID id);
    List<ChatResponse> findAll();
    ChatResponse update(UUID id, ChatRequest request);
    void delete(UUID id);
}
