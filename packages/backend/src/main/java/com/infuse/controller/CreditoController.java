package com.infuse.controller;

import com.infuse.dto.CreditDTO;
import com.infuse.dto.ErrorResponseDTO;
import com.infuse.service.CreditService;
import com.infuse.service.KafkaProducerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/credits")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Credit Management", description = "APIs for managing and querying constituted credits")
public class CreditoController {

  private final CreditService creditService;
  private final KafkaProducerService kafkaProducerService;

  @Operation(
      summary = "Find credits by NFS-e number",
      description = "Retrieves all credits associated with a specific NFS-e (invoice) number")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Credits found successfully",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CreditDTO.class))),
        @ApiResponse(
            responseCode = "404",
            description = "No credits found for the specified NFS-e number",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
      })
  @GetMapping("/{numeroNfse}")
  public ResponseEntity<List<CreditDTO>> buscarPorNumeroNfse(
      @Parameter(description = "NFS-e number to search for", example = "123456789") @PathVariable
          String numeroNfse) {
    log.info("Searching for credits with invoice number: {}", numeroNfse);
    kafkaProducerService.publicarConsulta("nfs-e", numeroNfse);
    List<CreditDTO> credits = creditService.findByInvoiceNumber(numeroNfse);
    if (credits.isEmpty()) {
      log.info("No credits found for invoice number: {}", numeroNfse);
      return ResponseEntity.notFound().build();
    }
    log.info("Found {} credits for invoice number: {}", credits.size(), numeroNfse);
    return ResponseEntity.ok(credits);
  }

  @Operation(
      summary = "Find credit by credit number",
      description = "Retrieves detailed information about a specific credit by its number")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Credit found successfully",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CreditDTO.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Credit not found",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class)))
      })
  @GetMapping("/credit/{numeroCredito}")
  public ResponseEntity<CreditDTO> buscarPorNumeroCredito(
      @Parameter(description = "Credit number to search for", example = "CRED-001") @PathVariable
          String numeroCredito) {
    log.info("Searching for credit with number: {}", numeroCredito);
    kafkaProducerService.publicarConsulta("credito", numeroCredito);
    CreditDTO credit = creditService.findByCreditNumber(numeroCredito);
    if (credit == null) {
      log.info("No credit found for number: {}", numeroCredito);
      return ResponseEntity.notFound().build();
    }
    log.info("Found credit with number: {}", numeroCredito);
    return ResponseEntity.ok(credit);
  }
}
