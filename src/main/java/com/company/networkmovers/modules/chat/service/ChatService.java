package com.company.networkmovers.modules.chat.service;

import com.company.networkmovers.modules.chat.dto.request.ChatRequest;
import com.company.networkmovers.modules.chat.dto.response.ChatResponse;
import java.util.List;

public interface ChatService {
    ChatResponse create(ChatRequest request);
    ChatResponse findById(Long id);
    List<ChatResponse> findAll();
    ChatResponse update(Long id, ChatRequest request);
    void delete(Long id);
}
