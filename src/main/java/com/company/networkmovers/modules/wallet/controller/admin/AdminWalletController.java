package com.company.networkmovers.modules.wallet.controller.admin;

import com.company.networkmovers.modules.wallet.dto.request.WalletRequest;
import com.company.networkmovers.modules.wallet.dto.response.WalletResponse;
import com.company.networkmovers.modules.wallet.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/wallet")
public class AdminWalletController {

    private final WalletService service;

    public AdminWalletController(WalletService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<WalletResponse> create(@RequestBody WalletRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<WalletResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<WalletResponse> update(@PathVariable Long id, @RequestBody WalletRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
