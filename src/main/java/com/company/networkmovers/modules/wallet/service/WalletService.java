package com.company.networkmovers.modules.wallet.service;

import com.company.networkmovers.modules.wallet.dto.request.WalletRequest;
import com.company.networkmovers.modules.wallet.dto.response.WalletResponse;
import java.util.List;

public interface WalletService {
    WalletResponse create(WalletRequest request);
    WalletResponse findById(Long id);
    List<WalletResponse> findAll();
    WalletResponse update(Long id, WalletRequest request);
    void delete(Long id);
}
