package com.infuse.service.impl;

import com.infuse.dto.CreditDTO;
import com.infuse.exception.ResourceNotFoundException;
import com.infuse.mapper.CreditMapper;
import com.infuse.model.Credit;
import com.infuse.repository.CreditRepository;
import com.infuse.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {
    
    private final CreditRepository creditRepository;
    private final CreditMapper creditMapper;
    
    @Override
    public List<CreditDTO> findByInvoiceNumber(String invoiceNumber) {
        List<Credit> credits = creditRepository.findByInvoiceNumber(invoiceNumber);
        
        if (credits.isEmpty()) {
            throw new ResourceNotFoundException("Credit", "invoiceNumber", invoiceNumber);
        }
        
        return creditMapper.toDTOList(credits);
    }
    
    @Override
    public CreditDTO findByCreditNumber(String creditNumber) {
        return creditRepository.findByCreditNumber(creditNumber)
                .map(creditMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Credit", "creditNumber", creditNumber));
    }
} 