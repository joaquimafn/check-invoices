package com.infuse.exception;

import com.infuse.dto.ErrorResponseDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(
      MethodArgumentNotValidException ex, WebRequest request) {
    log.error("Erro de validação: {}", ex.getMessage());

    List<String> details =
        ex.getBindingResult().getFieldErrors().stream()
            .map(FieldError::getDefaultMessage)
            .collect(Collectors.toList());

    ErrorResponseDTO errorResponse =
        ErrorResponseDTO.builder()
            .message("Erro de validação")
            .status(HttpStatus.BAD_REQUEST.value())
            .error("Validation Error")
            .path(request.getDescription(false))
            .timestamp(LocalDateTime.now())
            .details(details)
            .build();

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex, WebRequest request) {
    log.error("Erro interno do servidor: {}", ex.getMessage(), ex);

    ErrorResponseDTO errorResponse =
        ErrorResponseDTO.builder()
            .message("Erro interno do servidor")
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .error("Internal Server Error")
            .path(request.getDescription(false))
            .timestamp(LocalDateTime.now())
            .build();

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
  }
}
