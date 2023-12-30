package com.dailycodebuffer.paymentservice.controller;

import com.dailycodebuffer.paymentservice.model.PaymentRequest;
import com.dailycodebuffer.paymentservice.model.PaymentResponse;
import com.dailycodebuffer.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest) {
        return new ResponseEntity<>(paymentService.doPayment(paymentRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(@PathVariable("orderId") long orderId) {
        return new ResponseEntity<>(paymentService.getPaymentDetailsByOrderId(orderId), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PaymentResponse>> getPaymentDetailsByOrderId() {
        return new ResponseEntity<>(paymentService.getAllPaymentDetails(), HttpStatus.OK);
    }
}
