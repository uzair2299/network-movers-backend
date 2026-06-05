package com.company.networkmovers.modules.accounting.service;

import java.util.UUID;

import com.company.networkmovers.modules.accounting.dto.request.AccountingRequest;
import com.company.networkmovers.modules.accounting.dto.response.AccountingResponse;
import java.util.List;

public interface AccountingService {
    AccountingResponse create(AccountingRequest request);
    AccountingResponse findById(UUID id);
    List<AccountingResponse> findAll();
    AccountingResponse update(UUID id, AccountingRequest request);
    void delete(UUID id);
}
