package com.company.networkmovers.modules.chat.facade;

import com.company.networkmovers.modules.chat.dto.request.ChatRequest;
import com.company.networkmovers.modules.chat.dto.response.ChatResponse;
import com.company.networkmovers.modules.chat.service.ChatService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ChatFacade {

    private final ChatService service;

    public ChatFacade(ChatService service) {
        this.service = service;
    }

    public ChatResponse create(ChatRequest request) {
        return service.create(request);
    }

    public ChatResponse findById(Long id) {
        return service.findById(id);
    }

    public List<ChatResponse> findAll() {
        return service.findAll();
    }

    public ChatResponse update(Long id, ChatRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
