package com.company.networkmovers.modules.accounting.facade;

import com.company.networkmovers.modules.accounting.dto.request.AccountingRequest;
import com.company.networkmovers.modules.accounting.dto.response.AccountingResponse;
import com.company.networkmovers.modules.accounting.service.AccountingService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AccountingFacade {

    private final AccountingService service;

    public AccountingFacade(AccountingService service) {
        this.service = service;
    }

    public AccountingResponse create(AccountingRequest request) {
        return service.create(request);
    }

    public AccountingResponse findById(Long id) {
        return service.findById(id);
    }

    public List<AccountingResponse> findAll() {
        return service.findAll();
    }

    public AccountingResponse update(Long id, AccountingRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
