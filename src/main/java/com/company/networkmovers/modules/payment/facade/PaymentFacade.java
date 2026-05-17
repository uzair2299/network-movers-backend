package com.company.networkmovers.modules.payment.facade;

import com.company.networkmovers.modules.payment.dto.request.PaymentRequest;
import com.company.networkmovers.modules.payment.dto.response.PaymentResponse;
import com.company.networkmovers.modules.payment.service.PaymentService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PaymentFacade {

    private final PaymentService service;

    public PaymentFacade(PaymentService service) {
        this.service = service;
    }

    public PaymentResponse create(PaymentRequest request) {
        return service.create(request);
    }

    public PaymentResponse findById(Long id) {
        return service.findById(id);
    }

    public List<PaymentResponse> findAll() {
        return service.findAll();
    }

    public PaymentResponse update(Long id, PaymentRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
