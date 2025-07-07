package com.infuse.repository;

import com.infuse.model.Credit;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {

  /**
   * Finds all credits associated with a specific invoice number
   *
   * @param invoiceNumber the invoice number to search for
   * @return a list of credits associated with the given invoice number
   */
  List<Credit> findByInvoiceNumber(String invoiceNumber);

  /**
   * Finds a credit by its unique credit number
   *
   * @param creditNumber the credit number to search for
   * @return an Optional containing the credit if found, or empty otherwise
   */
  Optional<Credit> findByCreditNumber(String creditNumber);
}
