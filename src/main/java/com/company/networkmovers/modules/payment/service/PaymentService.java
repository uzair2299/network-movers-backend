package com.company.networkmovers.modules.payment.service;

import java.util.UUID;

import com.company.networkmovers.modules.payment.dto.request.PaymentRequest;
import com.company.networkmovers.modules.payment.dto.response.PaymentResponse;
import java.util.List;

public interface PaymentService {
    PaymentResponse create(PaymentRequest request);
    PaymentResponse findById(UUID id);
    List<PaymentResponse> findAll();
    PaymentResponse update(UUID id, PaymentRequest request);
    void delete(UUID id);
}
