package com.infuse.repository;

import com.infuse.model.Credito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditoRepository extends JpaRepository<Credito, Long> {
    
    /**
     * Finds all credits associated with a specific invoice number
     * 
     * @param numeroNfse the invoice number to search for
     * @return a list of credits associated with the given invoice number
     */
    List<Credito> findByNumeroNfse(String numeroNfse);
    
    /**
     * Finds a credit by its unique credit number
     * 
     * @param numeroCredito the credit number to search for
     * @return an Optional containing the credit if found, or empty otherwise
     */
    Optional<Credito> findByNumeroCredito(String numeroCredito);
} 