package com.example.webriseapi.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SubscriptionDTO {
    private Long id;
    @NotBlank(message = "Service name is mandatory")
    private String serviceName;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotNull(message = "Price is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "Start date is mandatory")
    @PastOrPresent(message = "Start date should be in the past or present")
    private LocalDate startDate;

    @NotNull(message = "End date is mandatory")
    @Future(message = "End date should be in the future")
    private LocalDate endDate;

}