package com.infuse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.infuse.repository")
@EnableTransactionManagement
public class DatabaseConfig {
    // Configurações do banco de dados
    // Esta classe será expandida conforme necessário
} 