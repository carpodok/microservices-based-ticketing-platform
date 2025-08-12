package com.atc.catalog.dto;

import lombok.*;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VenueDto {
    private Long id;
    private String name;
    private String city;
    private OffsetDateTime createdAt;
}
