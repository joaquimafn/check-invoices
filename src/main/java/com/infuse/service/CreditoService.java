package com.infuse.service;

import com.infuse.dto.CreditoDTO;

import java.util.List;

public interface CreditoService {

    /**
     * Busca todos os créditos associados a um número de NFS-e
     *
     * @param numeroNfse o número da NFS-e para buscar
     * @return uma lista de DTOs de créditos associados ao número de NFS-e fornecido
     * @throws com.infuse.exception.RecursoNaoEncontradoException se nenhum crédito for encontrado
     */
    List<CreditoDTO> buscarPorNumeroNfse(String numeroNfse);
    
    /**
     * Busca um crédito específico pelo seu número
     *
     * @param numeroCredito o número do crédito a ser buscado
     * @return o DTO do crédito encontrado
     * @throws com.infuse.exception.RecursoNaoEncontradoException se o crédito não for encontrado
     */
    CreditoDTO buscarPorNumeroCredito(String numeroCredito);
} 