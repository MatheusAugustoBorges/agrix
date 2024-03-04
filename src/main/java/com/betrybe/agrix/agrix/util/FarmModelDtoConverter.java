package com.betrybe.agrix.agrix.util;

import com.betrybe.agrix.agrix.controllers.dto.FarmCreationDto;
import com.betrybe.agrix.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.agrix.models.entities.Farm;

/**
 * Utility farm class to convert between model and dto.
 */
public class FarmModelDtoConverter {

  /**
   * Convert from model to dto.
   */
  public static FarmDto farmToFarmDto(Farm farm) {
    return new FarmDto(
        farm.getId(),
        farm.getName(),
        farm.getSize()
    );
  }

  /**
   * Convert from dto to model.
   */
  public static Farm farmDtoToFarm(FarmDto farmDto) {
    Farm farm = new Farm();

    farm.setId(farmDto.id());
    farm.setName(farmDto.name());
    farm.setSize(farmDto.size());

    return farm;
  }

  /**
   * Convert from creation dto to model.
   */
  public static Farm farmCreationDtoToFarm(FarmCreationDto farmCreationDto) {
    Farm farm = new Farm();

    farm.setName(farmCreationDto.name());
    farm.setSize(farmCreationDto.size());

    return farm;
  }

}
