package com.company.networkmovers.modules.wallet.facade;

import com.company.networkmovers.modules.wallet.dto.request.WalletRequest;
import com.company.networkmovers.modules.wallet.dto.response.WalletResponse;
import com.company.networkmovers.modules.wallet.service.WalletService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class WalletFacade {

    private final WalletService service;

    public WalletFacade(WalletService service) {
        this.service = service;
    }

    public WalletResponse create(WalletRequest request) {
        return service.create(request);
    }

    public WalletResponse findById(Long id) {
        return service.findById(id);
    }

    public List<WalletResponse> findAll() {
        return service.findAll();
    }

    public WalletResponse update(Long id, WalletRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
