package com.infuse.service;

import com.infuse.dto.ConsultaCreditoEventoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {

  @Value("${app.kafka.topic.consulta-creditos}")
  private String consultaCreditosTopic;

  private final KafkaTemplate<String, Object> kafkaTemplate;

  public void publicarConsulta(String tipoConsulta, String parametro) {
    ConsultaCreditoEventoDTO evento =
        ConsultaCreditoEventoDTO.builder()
            .tipoConsulta(tipoConsulta)
            .parametro(parametro)
            .timestamp(java.time.LocalDateTime.now())
            .build();
    try {
      kafkaTemplate.send(consultaCreditosTopic, evento);
      log.info("Consulta event published to topic {}: {}", consultaCreditosTopic, evento);
    } catch (Exception e) {
      log.error("Error publishing consulta event to Kafka", e);
    }
  }
}
