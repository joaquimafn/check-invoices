package com.infuse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Error Response Data Transfer Object")
public class ErrorResponseDTO {

    @Schema(description = "Error message", example = "Credit not found")
    private String message;

    @Schema(description = "HTTP status code", example = "404")
    private int status;

    @Schema(description = "Error type", example = "Not Found")
    private String error;

    @Schema(description = "Request path that caused the error", example = "/api/credits/INVALID-001")
    private String path;

    @Schema(description = "Timestamp when the error occurred", example = "2023-10-15T10:30:00")
    private LocalDateTime timestamp;

    @Schema(description = "Additional error details", example = "[\"Field validation failed\"]")
    private List<String> details;
}