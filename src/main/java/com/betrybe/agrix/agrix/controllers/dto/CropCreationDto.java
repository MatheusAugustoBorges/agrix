package com.betrybe.agrix.agrix.controllers.dto;

import java.time.LocalDate;

/**
 * DTO of creation Crop.
 */
public record CropCreationDto(
    String name,
    Double plantedArea,
    LocalDate plantedDate,
    LocalDate harvestDate) {
}
