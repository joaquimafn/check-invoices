package com.infuse.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "credits")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Credit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String creditNumber;
  private String invoiceNumber;
  private String constitutionDate;
  private BigDecimal issqnValue;
  private String creditType;
  private boolean simplifiedTaxSystem;
  private BigDecimal taxRate;
  private BigDecimal invoicedValue;
  private BigDecimal deductionValue;
  private BigDecimal taxableAmount;
}
