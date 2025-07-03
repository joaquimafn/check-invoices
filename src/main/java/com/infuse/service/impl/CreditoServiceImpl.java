package com.infuse.service.impl;

import com.infuse.dto.CreditoDTO;
import com.infuse.exception.RecursoNaoEncontradoException;
import com.infuse.mapper.CreditoMapper;
import com.infuse.model.Credito;
import com.infuse.repository.CreditoRepository;
import com.infuse.service.CreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditoServiceImpl implements CreditoService {
    
    private final CreditoRepository creditoRepository;
    private final CreditoMapper creditoMapper;
    
    @Override
    public List<CreditoDTO> buscarPorNumeroNfse(String numeroNfse) {
        List<Credito> creditos = creditoRepository.findByNumeroNfse(numeroNfse);
        
        if (creditos.isEmpty()) {
            throw new RecursoNaoEncontradoException("Crédito", "numeroNfse", numeroNfse);
        }
        
        return creditoMapper.toDTOList(creditos);
    }
    
    @Override
    public CreditoDTO buscarPorNumeroCredito(String numeroCredito) {
        return creditoRepository.findByNumeroCredito(numeroCredito)
                .map(creditoMapper::toDTO)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Crédito", "numeroCredito", numeroCredito));
    }
} 