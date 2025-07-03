package com.infuse.service;

import com.infuse.dto.CreditDTO;

import java.util.List;

public interface CreditService {

    /**
     * Finds all credits associated with a specific invoice number
     *
     * @param invoiceNumber the invoice number to search for
     * @return a list of DTOs of credits associated with the given invoice number
     * @throws com.infuse.exception.ResourceNotFoundException if no credits are found
     */
    List<CreditDTO> findByInvoiceNumber(String invoiceNumber);
    
    /**
     * Finds a specific credit by its number
     *
     * @param creditNumber the credit number to search for
     * @return the DTO of the found credit
     * @throws com.infuse.exception.ResourceNotFoundException if the credit is not found
     */
    CreditDTO findByCreditNumber(String creditNumber);
} 