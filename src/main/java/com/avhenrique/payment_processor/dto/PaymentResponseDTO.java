package com.avhenrique.payment_processor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO {

    private Long id;
    private String externalId;
    private BigDecimal amount;
    private String currency;
    private String status;
    private String payerEmail;
    private String payeeEmail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}