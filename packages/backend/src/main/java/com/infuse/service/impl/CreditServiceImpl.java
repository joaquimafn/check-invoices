package com.infuse.service.impl;

import com.infuse.dto.CreditDTO;
import com.infuse.exception.ResourceNotFoundException;
import com.infuse.mapper.CreditMapper;
import com.infuse.model.Credit;
import com.infuse.repository.CreditRepository;
import com.infuse.service.CreditService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {

  private final CreditRepository creditRepository;
  private final CreditMapper creditMapper;

  @Override
  public List<CreditDTO> findByInvoiceNumber(String invoiceNumber) {
    List<Credit> credits = creditRepository.findDistinctByInvoiceNumber(invoiceNumber);

    if (credits.isEmpty()) {
      throw new ResourceNotFoundException("Credit", "invoiceNumber", invoiceNumber);
    }

    return creditMapper.toDTOList(credits);
  }

  @Override
  public CreditDTO findByCreditNumber(String creditNumber) {
    return creditRepository
        .findFirstByCreditNumber(creditNumber)
        .map(creditMapper::toDTO)
        .orElseThrow(() -> new ResourceNotFoundException("Credit", "creditNumber", creditNumber));
  }
}
