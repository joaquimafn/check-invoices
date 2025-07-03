package com.infuse.controller;

import com.infuse.dto.CreditDTO;
import com.infuse.service.CreditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
@RequiredArgsConstructor
@Slf4j
public class CreditoController {

    private final CreditService creditService;

    /**
     * Finds all credits associated with a specific invoice number
     *
     * @param numeroNfse the invoice number to search for
     * @return ResponseEntity containing a list of CreditDTO objects
     */
    @GetMapping("/{numeroNfse}")
    public ResponseEntity<List<CreditDTO>> buscarPorNumeroNfse(@PathVariable String numeroNfse) {
        log.info("Searching for credits with invoice number: {}", numeroNfse);
        List<CreditDTO> credits = creditService.findByInvoiceNumber(numeroNfse);
        log.info("Found {} credits for invoice number: {}", credits.size(), numeroNfse);
        return ResponseEntity.ok(credits);
    }

    /**
     * Finds a specific credit by its number
     *
     * @param numeroCredito the credit number to search for
     * @return ResponseEntity containing the CreditDTO object
     */
    @GetMapping("/credit/{numeroCredito}")
    public ResponseEntity<CreditDTO> buscarPorNumeroCredito(@PathVariable String numeroCredito) {
        log.info("Searching for credit with number: {}", numeroCredito);
        CreditDTO credit = creditService.findByCreditNumber(numeroCredito);
        log.info("Found credit with number: {}", numeroCredito);
        return ResponseEntity.ok(credit);
    }
} 