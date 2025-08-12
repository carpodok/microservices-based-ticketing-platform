package com.atc.catalog.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceTierDto {
    private Long id;
    private Long eventId;
    private String name;
    private BigDecimal price;
    private String currency; // ISO 4217 (e.g., "USD", "TRY")
}
