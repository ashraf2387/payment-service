package com.dailycodebuffer.paymentservice.service;

import com.dailycodebuffer.paymentservice.model.PaymentRequest;
import com.dailycodebuffer.paymentservice.model.PaymentResponse;

import java.util.List;

public interface PaymentService {
    long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(long orderId);

    List<PaymentResponse> getAllPaymentDetails();
}
