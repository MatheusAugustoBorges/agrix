package com.betrybe.agrix.agrix.util;

import com.betrybe.agrix.agrix.controllers.dto.FertilizerCreationDto;
import com.betrybe.agrix.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.agrix.models.entities.Fertilizer;

/**
 * Utility fertilizer class to convert between model and dto.
 */
public class FertilizerModelDtoConverter {

  /**
   * Convert from model to dto.
   */
  public static FertilizerDto fertilizerToFertilizerDto(Fertilizer fertilizer) {
    return new FertilizerDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()
    );
  }

  /**
   * Convert from dto to model.
   */
  public static Fertilizer fertilizerDtoToFertilizer(FertilizerDto fertilizerDto) {
    Fertilizer fertilizer = new Fertilizer();

    fertilizer.setId(fertilizerDto.id());
    fertilizer.setName(fertilizerDto.name());
    fertilizer.setBrand(fertilizerDto.brand());
    fertilizer.setComposition(fertilizerDto.composition());

    return fertilizer;
  }

  /**
   * Convert from creation dto to model.
   */
  public static Fertilizer fertilizerCreationDtoToFertilizer(
      FertilizerCreationDto fertilizerCreationDto) {
    Fertilizer fertilizer = new Fertilizer();

    fertilizer.setName(fertilizerCreationDto.name());
    fertilizer.setBrand(fertilizerCreationDto.brand());
    fertilizer.setComposition(fertilizerCreationDto.composition());

    return fertilizer;
  }

}
