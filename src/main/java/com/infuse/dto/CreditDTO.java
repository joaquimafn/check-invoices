package com.infuse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditDTO {
    private Long id;
    private String creditNumber;
    private String invoiceNumber;
    private LocalDate constitutionDate;
    private BigDecimal issqnValue;
    private String creditType;
    private boolean simplifiedTaxSystem;
    private BigDecimal taxRate;
    private BigDecimal invoicedValue;
    private BigDecimal deductionValue;
    private BigDecimal taxableAmount;
} 