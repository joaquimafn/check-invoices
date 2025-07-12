package com.infuse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Credit Data Transfer Object")
public class CreditDTO {

  @Schema(description = "Unique identifier of the credit", example = "1")
  private Long id;

  @Schema(description = "Credit number", example = "CRED-001")
  private String creditNumber;

  @Schema(description = "Invoice number (NFS-e)", example = "123456789")
  private String invoiceNumber;

  @Schema(description = "Date when the credit was constituted", example = "2023-10-15")
  private String constitutionDate;

  @Schema(description = "ISSQN value", example = "150.00")
  private BigDecimal issqnValue;

  @Schema(description = "Type of credit", example = "ISSQN")
  private String creditType;

  @Schema(description = "Indicates if it's under simplified tax system", example = "true")
  private boolean simplifiedTaxSystem;

  @Schema(description = "Tax rate applied", example = "0.05")
  private BigDecimal taxRate;

  @Schema(description = "Total invoiced value", example = "3000.00")
  private BigDecimal invoicedValue;

  @Schema(description = "Deduction value", example = "0.00")
  private BigDecimal deductionValue;

  @Schema(description = "Taxable amount for calculation", example = "3000.00")
  private BigDecimal taxableAmount;
}
