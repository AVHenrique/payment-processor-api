package com.avhenrique.payment_processor.service;

import com.avhenrique.payment_processor.dto.PaymentRequestDTO;
import com.avhenrique.payment_processor.dto.PaymentResponseDTO;
import com.avhenrique.payment_processor.entity.Payment;
import com.avhenrique.payment_processor.kafka.PaymentProducer;
import com.avhenrique.payment_processor.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentProducer paymentProducer;

    public PaymentResponseDTO createPayment(PaymentRequestDTO request) {
        Payment payment = new Payment();
        payment.setExternalId(request.getExternalId());
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());
        payment.setPayerEmail(request.getPayerEmail());
        payment.setPayeeEmail(request.getPayeeEmail());

        Payment savedPayment = paymentRepository.save(payment);
        paymentProducer.sendPaymentCreatedEvent(savedPayment);

        return mapToDTO(savedPayment);
    }

    public PaymentResponseDTO getPaymentByExternalId(String externalId) {
        Optional<Payment> payment = paymentRepository.findByExternalId(externalId);
        return payment.map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Payment not found: " + externalId));
    }

    public PaymentResponseDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found: " + id));
        return mapToDTO(payment);
    }

    public PaymentResponseDTO updatePaymentStatus(Long id, String status) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found: " + id));

        payment.setStatus(status);
        Payment updatedPayment = paymentRepository.save(payment);
        paymentProducer.sendPaymentStatusUpdatedEvent(updatedPayment);

        return mapToDTO(updatedPayment);
    }

    private PaymentResponseDTO mapToDTO(Payment payment) {
        return new PaymentResponseDTO(
                payment.getId(),
                payment.getExternalId(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getStatus(),
                payment.getPayerEmail(),
                payment.getPayeeEmail(),
                payment.getCreatedAt(),
                payment.getUpdatedAt()
        );
    }
}