package com.infuse.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

  @Value("${app.kafka.topic.consulta-creditos}")
  private String consultaCreditosTopic;

  @Bean
  public NewTopic consultaCreditosTopic() {
    return TopicBuilder.name(consultaCreditosTopic).partitions(1).replicas(1).build();
  }
}
