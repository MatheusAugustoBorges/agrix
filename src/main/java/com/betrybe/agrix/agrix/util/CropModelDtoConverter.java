package com.betrybe.agrix.agrix.util;

import com.betrybe.agrix.agrix.controllers.dto.CropCreationDto;
import com.betrybe.agrix.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.agrix.models.entities.Crop;

/**
 * Converter of Crop model and Crop DTO.
 */
public class CropModelDtoConverter {

  /**
   * Convert from model to dto.
   */
  public static CropDto cropToCropDto(Crop crop) {
    return new CropDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getFarm().getId(),
        crop.getPlantedDate(),
        crop.getHarvestDate()
    );
  }

  /**
   * Convert from dto to model.
   */
  public static Crop cropDtoToCrop(CropDto cropDto) {
    Crop crop = new Crop();

    crop.setId(cropDto.id());
    crop.setName(cropDto.name());
    crop.setPlantedArea(cropDto.plantedArea());
    crop.setPlantedDate(cropDto.plantedDate());
    crop.setHarvestDate(cropDto.harvestDate());

    return crop;
  }

  /**
   * Convert from creation dto to model.
   */
  public static Crop cropCreationDtoToCrop(CropCreationDto cropCreationDto) {
    Crop crop = new Crop();

    crop.setName(cropCreationDto.name());
    crop.setPlantedArea(cropCreationDto.plantedArea());
    crop.setPlantedDate(cropCreationDto.plantedDate());
    crop.setHarvestDate(cropCreationDto.harvestDate());

    return crop;
  }

}
