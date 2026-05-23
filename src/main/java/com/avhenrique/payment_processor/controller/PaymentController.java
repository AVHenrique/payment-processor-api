package com.avhenrique.payment_processor.controller;

import com.avhenrique.payment_processor.dto.PaymentRequestDTO;
import com.avhenrique.payment_processor.dto.PaymentResponseDTO;
import com.avhenrique.payment_processor.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> createPayment(@Valid @RequestBody PaymentRequestDTO request) {
        PaymentResponseDTO response = paymentService.createPayment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> getPaymentById(@PathVariable Long id) {
        PaymentResponseDTO response = paymentService.getPaymentById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/external/{externalId}")
    public ResponseEntity<PaymentResponseDTO> getPaymentByExternalId(@PathVariable String externalId) {
        PaymentResponseDTO response = paymentService.getPaymentByExternalId(externalId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PaymentResponseDTO> updatePaymentStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        PaymentResponseDTO response = paymentService.updatePaymentStatus(id, status);
        return ResponseEntity.ok(response);
    }
}