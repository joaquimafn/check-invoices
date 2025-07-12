package com.infuse.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConsultaCreditoEventoDTO {
  private String tipoConsulta;
  private String parametro;
  private LocalDateTime timestamp;
}
