package com.company.networkmovers.modules.payment.service;

import com.company.networkmovers.modules.payment.dto.request.PaymentRequest;
import com.company.networkmovers.modules.payment.dto.response.PaymentResponse;
import java.util.List;

public interface PaymentService {
    PaymentResponse create(PaymentRequest request);
    PaymentResponse findById(Long id);
    List<PaymentResponse> findAll();
    PaymentResponse update(Long id, PaymentRequest request);
    void delete(Long id);
}
