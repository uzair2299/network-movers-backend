package com.company.networkmovers.modules.chat.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.chat.dto.response.ChatResponse;
import com.company.networkmovers.modules.chat.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/chat")
public class PublicChatController {

    private final ChatService service;

    public PublicChatController(ChatService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ChatResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
