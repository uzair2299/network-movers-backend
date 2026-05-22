package com.company.networkmovers.modules.wallet.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.wallet.dto.response.WalletResponse;
import com.company.networkmovers.modules.wallet.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/wallet")
public class PublicWalletController {

    private final WalletService service;

    public PublicWalletController(WalletService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<WalletResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

