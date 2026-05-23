package com.avhenrique.payment_processor.kafka;

import com.avhenrique.payment_processor.entity.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private static final String PAYMENT_CREATED_TOPIC = "payment-created";
    private static final String PAYMENT_STATUS_UPDATED_TOPIC = "payment-status-updated";

    public void sendPaymentCreatedEvent(Payment payment) {
        try {
            String payload = objectMapper.writeValueAsString(payment);
            kafkaTemplate.send(PAYMENT_CREATED_TOPIC, payment.getExternalId(), payload);
            log.info("Payment created event sent: {}", payment.getExternalId());
        } catch (Exception e) {
            log.error("Error sending payment created event", e);
        }
    }

    public void sendPaymentStatusUpdatedEvent(Payment payment) {
        try {
            String payload = objectMapper.writeValueAsString(payment);
            kafkaTemplate.send(PAYMENT_STATUS_UPDATED_TOPIC, payment.getExternalId(), payload);
            log.info("Payment status updated event sent: {}", payment.getExternalId());
        } catch (Exception e) {
            log.error("Error sending payment status updated event", e);
        }
    }
}