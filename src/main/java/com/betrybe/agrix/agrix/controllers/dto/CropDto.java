package com.betrybe.agrix.agrix.controllers.dto;

import java.time.LocalDate;

/**
 * DTO of Crop.
 */
public record CropDto(
    Long id,
    String name,
    Double plantedArea,
    Long farmId,
    LocalDate plantedDate,
    LocalDate harvestDate) {
}
