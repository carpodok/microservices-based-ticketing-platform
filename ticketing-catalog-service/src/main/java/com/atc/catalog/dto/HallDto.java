package com.atc.catalog.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HallDto {
    private Long id;
    private Long venueId;
    private String name;
}
