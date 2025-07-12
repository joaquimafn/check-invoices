package com.infuse.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.infuse.dto.CreditDTO;
import com.infuse.exception.ResourceNotFoundException;
import com.infuse.mapper.CreditMapper;
import com.infuse.model.Credit;
import com.infuse.repository.CreditRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreditServiceImplTest {

  @Mock private CreditRepository creditRepository;

  @Mock private CreditMapper creditMapper;

  @InjectMocks private CreditServiceImpl creditService;

  private Credit credit;
  private CreditDTO creditDTO;

  @BeforeEach
  void setUp() {
    credit = new Credit();
    credit.setCreditNumber("123");
    credit.setInvoiceNumber("INV-001");

    creditDTO = new CreditDTO();
    creditDTO.setCreditNumber("123");
    creditDTO.setInvoiceNumber("INV-001");
  }

  @Test
  void findByInvoiceNumberShouldReturnListOfCreditDTOWhenCreditsExist() {
    when(creditRepository.findByInvoiceNumber(anyString())).thenReturn(List.of(credit));
    when(creditMapper.toDTOList(anyList())).thenReturn(List.of(creditDTO));

    List<CreditDTO> result = creditService.findByInvoiceNumber("INV-001");

    assertFalse(result.isEmpty());
    assertEquals(1, result.size());
    assertEquals("123", result.get(0).getCreditNumber());
    verify(creditRepository).findByInvoiceNumber("INV-001");
  }

  @Test
  void findByInvoiceNumberShouldThrowResourceNotFoundExceptionWhenNoCreditsExist() {
    when(creditRepository.findByInvoiceNumber(anyString())).thenReturn(Collections.emptyList());

    assertThrows(
        ResourceNotFoundException.class, () -> creditService.findByInvoiceNumber("INV-002"));

    verify(creditRepository).findByInvoiceNumber("INV-002");
  }

  @Test
  void findByCreditNumberShouldReturnCreditDTOWhenCreditExists() {
    when(creditRepository.findByCreditNumber(anyString())).thenReturn(Optional.of(credit));
    when(creditMapper.toDTO(any(Credit.class))).thenReturn(creditDTO);

    CreditDTO result = creditService.findByCreditNumber("123");

    assertNotNull(result);
    assertEquals("123", result.getCreditNumber());
    verify(creditRepository).findByCreditNumber("123");
  }

  @Test
  void findByCreditNumberShouldThrowResourceNotFoundExceptionWhenCreditDoesNotExist() {
    when(creditRepository.findByCreditNumber(anyString())).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> creditService.findByCreditNumber("456"));

    verify(creditRepository).findByCreditNumber("456");
  }
}
