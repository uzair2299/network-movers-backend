package com.company.networkmovers.modules.accounting.service;

import com.company.networkmovers.modules.accounting.dto.request.AccountingRequest;
import com.company.networkmovers.modules.accounting.dto.response.AccountingResponse;
import java.util.List;

public interface AccountingService {
    AccountingResponse create(AccountingRequest request);
    AccountingResponse findById(Long id);
    List<AccountingResponse> findAll();
    AccountingResponse update(Long id, AccountingRequest request);
    void delete(Long id);
}
