package com.company.networkmovers.modules.wallet.service;

import java.util.UUID;

import com.company.networkmovers.modules.wallet.dto.request.WalletRequest;
import com.company.networkmovers.modules.wallet.dto.response.WalletResponse;
import java.util.List;

public interface WalletService {
    WalletResponse create(WalletRequest request);
    WalletResponse findById(UUID id);
    List<WalletResponse> findAll();
    WalletResponse update(UUID id, WalletRequest request);
    void delete(UUID id);
}
