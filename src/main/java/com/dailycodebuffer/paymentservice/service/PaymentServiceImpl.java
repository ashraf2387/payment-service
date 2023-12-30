package com.dailycodebuffer.paymentservice.service;

import com.dailycodebuffer.paymentservice.entity.TransactionDetails;
import com.dailycodebuffer.paymentservice.model.PaymentRequest;
import com.dailycodebuffer.paymentservice.model.PaymentResponse;
import com.dailycodebuffer.paymentservice.repository.TransactionDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;
    @Override
    @Transactional
    public long doPayment(PaymentRequest paymentRequest) {
        TransactionDetails transactionDetails = TransactionDetails.builder()
                .paymentDate(Instant.now())
                .orderId(paymentRequest.getOrderId())
                .paymentMode(paymentRequest.getPaymentMode().name())
                .amount(paymentRequest.getAmount())
                .paymentStatus("SUCCESS")
                .referenceNumber(paymentRequest.getReferenceNumber())
                .build();

        transactionDetails = transactionDetailsRepository.save(transactionDetails);

        return transactionDetails.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(long orderId) {
        TransactionDetails transactionDetails = transactionDetailsRepository.findByOrderId(orderId);
        return getPaymentResponse(transactionDetails);
    }

    @Override
    public List<PaymentResponse> getAllPaymentDetails() {
        return transactionDetailsRepository.findAll()
                .stream()
                .map(this::getPaymentResponse)
                .collect(Collectors.toList());
    }

    private PaymentResponse getPaymentResponse(TransactionDetails transactionDetails) {
        return PaymentResponse.builder()
                .paymentId(transactionDetails.getId())
                .paymentDate(transactionDetails.getPaymentDate())
                .status(transactionDetails.getPaymentStatus())
                .paymentMode(transactionDetails.getPaymentMode())
                .amount(transactionDetails.getAmount())
                .orderId(transactionDetails.getOrderId())
                .build();
    }
}
