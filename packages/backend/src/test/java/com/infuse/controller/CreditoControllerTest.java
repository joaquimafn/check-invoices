package com.infuse.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.infuse.dto.CreditDTO;
import com.infuse.exception.ResourceNotFoundException;
import com.infuse.service.CreditService;
import com.infuse.service.KafkaProducerService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CreditoController.class)
class CreditoControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private CreditService creditService;

  @MockBean private KafkaProducerService kafkaProducerService;

  private CreditDTO creditDTO;

  @BeforeEach
  void setUp() {
    creditDTO = new CreditDTO();
    creditDTO.setCreditNumber("123");
    creditDTO.setInvoiceNumber("INV-001");
    doNothing().when(kafkaProducerService).publicarConsulta(anyString(), anyString());
  }

  @Test
  void buscarPorNumeroNfseShouldReturnOkWhenCreditsExist() throws Exception {
    when(creditService.findByInvoiceNumber("INV-001")).thenReturn(List.of(creditDTO));

    mockMvc
        .perform(get("/api/v1/credits/{numeroNfse}", "INV-001"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].creditNumber").value("123"));
  }

  @Test
  void buscarPorNumeroNfseShouldReturnNotFoundWhenNoCreditsExist() throws Exception {
    when(creditService.findByInvoiceNumber("INV-002")).thenReturn(Collections.emptyList());

    mockMvc
        .perform(get("/api/v1/credits/{numeroNfse}", "INV-002"))
        .andExpect(status().isNotFound());
  }

  @Test
  void buscarPorNumeroNfseShouldReturnNotFoundWhenServiceThrowsException() throws Exception {
    when(creditService.findByInvoiceNumber("INV-003"))
        .thenThrow(new ResourceNotFoundException("Credit", "invoiceNumber", "INV-003"));

    mockMvc
        .perform(get("/api/v1/credits/{numeroNfse}", "INV-003"))
        .andExpect(status().isNotFound());
  }

  @Test
  void buscarPorNumeroCreditoShouldReturnOkWhenCreditExists() throws Exception {
    when(creditService.findByCreditNumber("123")).thenReturn(creditDTO);

    mockMvc
        .perform(get("/api/v1/credits/credit/{numeroCredito}", "123"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.creditNumber").value("123"));
  }

  @Test
  void buscarPorNumeroCreditoShouldReturnNotFoundWhenCreditDoesNotExist() throws Exception {
    when(creditService.findByCreditNumber("456")).thenReturn(null);

    mockMvc
        .perform(get("/api/v1/credits/credit/{numeroCredito}", "456"))
        .andExpect(status().isNotFound());
  }

  @Test
  void buscarPorNumeroCreditoShouldReturnNotFoundWhenServiceThrowsException() throws Exception {
    when(creditService.findByCreditNumber("789"))
        .thenThrow(new ResourceNotFoundException("Credit", "creditNumber", "789"));

    mockMvc
        .perform(get("/api/v1/credits/credit/{numeroCredito}", "789"))
        .andExpect(status().isNotFound());
  }
}
