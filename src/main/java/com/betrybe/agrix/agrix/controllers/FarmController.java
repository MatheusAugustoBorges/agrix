package com.betrybe.agrix.agrix.controllers;

import static com.betrybe.agrix.agrix.util.CropModelDtoConverter.cropCreationDtoToCrop;
import static com.betrybe.agrix.agrix.util.CropModelDtoConverter.cropToCropDto;
import static com.betrybe.agrix.agrix.util.FarmModelDtoConverter.farmCreationDtoToFarm;
import static com.betrybe.agrix.agrix.util.FarmModelDtoConverter.farmToFarmDto;

import com.betrybe.agrix.agrix.controllers.dto.CropCreationDto;
import com.betrybe.agrix.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.agrix.controllers.dto.FarmCreationDto;
import com.betrybe.agrix.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.agrix.models.entities.Crop;
import com.betrybe.agrix.agrix.models.entities.Farm;
import com.betrybe.agrix.agrix.services.CropService;
import com.betrybe.agrix.agrix.services.FarmService;
import com.betrybe.agrix.agrix.util.CropModelDtoConverter;
import com.betrybe.agrix.agrix.util.FarmModelDtoConverter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller of Farm.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;
  private final CropService cropService;

  @Autowired
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  // ----------------------------------------------------------
  // Implementing methods to interact with FarmRepository

  /**
   * Method used to create a farm in the database.
   */
  @PostMapping()
  public ResponseEntity<FarmDto> createFarm(@RequestBody FarmCreationDto farmCreationDto) {
    //    Farm newFarm = new Farm();
    //    newFarm.setName(farmCreationDto.name());
    //    newFarm.setSize(farmCreationDto.size());
    //
    //    Farm farm = farmService.insertFarm(newFarm);
    //
    //    FarmDto farmDto = new FarmDto(farm.getId(), farm.getName(), farm.getSize());
    //
    //    return ResponseEntity.status(HttpStatus.CREATED).body(farmDto);

    Farm farm = farmService.insertFarm(farmCreationDtoToFarm(farmCreationDto));

    FarmDto farmDto = farmToFarmDto(farm);

    return ResponseEntity.status(HttpStatus.CREATED).body(farmDto);
  }

  /**
   * Method used to get all farms from the database.
   */
  @GetMapping()
  //  @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER"})
  @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
  public List<FarmDto> getAllFarms() {
    //    return farmService.getAllFarms().stream()
    //        .map(farm -> new FarmDto(farm.getId(), farm.getName(), farm.getSize()))
    //        .collect(Collectors.toList());

    //    return farmService.getAllFarms().stream()
    //        .map(farm -> FarmToFarmDto(farm))
    //        .collect(Collectors.toList());

    return farmService.getAllFarms().stream()
        .map(FarmModelDtoConverter::farmToFarmDto)
        .collect(Collectors.toList());
  }

  /**
   * Method used to get farm by id from the database.
   */
  @GetMapping("/{farmId}")
  public ResponseEntity<FarmDto> getFarmById(@PathVariable Long farmId) {
    Farm farm = farmService.getFarmById(farmId);
    return ResponseEntity.status(HttpStatus.OK).body(farmToFarmDto(farm));
  }

  // ----------------------------------------------------------
  // Implementing methods to interact with CropRepository

  /**
   * Method used to create a crop in a farm in the database.
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> createCropInFarm(
        @PathVariable Long farmId,
        @RequestBody CropCreationDto cropCreationDto) {
    Crop crop = farmService.postCropInFarm(farmId, cropCreationDtoToCrop(cropCreationDto));

    CropDto cropDto = cropToCropDto(crop);

    return ResponseEntity.status(HttpStatus.CREATED).body(cropDto);
  }

  /**
   * Method used to get all crops of a farm.
   */
  @GetMapping("/{farmId}/crops")
  public List<CropDto> getAllCropsInFarm(@PathVariable Long farmId) {
    return farmService.getAllCropsOfFarm(farmId).stream()
        .map(CropModelDtoConverter::cropToCropDto)
        .collect(Collectors.toList());
  }
}
