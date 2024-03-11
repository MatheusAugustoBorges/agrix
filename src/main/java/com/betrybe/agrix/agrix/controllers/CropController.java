package com.betrybe.agrix.agrix.controllers;

import static com.betrybe.agrix.agrix.util.CropModelDtoConverter.cropCreationDtoToCrop;
import static com.betrybe.agrix.agrix.util.CropModelDtoConverter.cropToCropDto;

import com.betrybe.agrix.agrix.controllers.dto.CropCreationDto;
import com.betrybe.agrix.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.agrix.models.entities.Crop;
import com.betrybe.agrix.agrix.services.CropService;
import com.betrybe.agrix.agrix.util.CropModelDtoConverter;
import com.betrybe.agrix.agrix.util.FertilizerModelDtoConverter;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller of Crop.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Method used to create a crop in the database.
   */
  @PostMapping()
  public ResponseEntity<CropDto> createCrop(@RequestBody CropCreationDto cropCreationDto) {
    Crop crop = cropService.insertCrop(cropCreationDtoToCrop(cropCreationDto));

    CropDto cropDto = cropToCropDto(crop);

    return ResponseEntity.status(HttpStatus.CREATED).body(cropDto);
  }

  /**
   * Method used to get all farms from the database.
   */
  @GetMapping()
  //  @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
  @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
  public List<CropDto> getAllCrops() {
    return cropService.getAllCrops().stream()
        .map(CropModelDtoConverter::cropToCropDto)
        .collect(Collectors.toList());
  }

  /**
   * Method used to get farm by id from the database.
   */
  @GetMapping("/{cropId}")
  public ResponseEntity<CropDto> getCropById(@PathVariable Long cropId) {
    Crop crop = cropService.getCropById(cropId);
    return ResponseEntity.status(HttpStatus.OK).body(cropToCropDto(crop));
  }

  /**
   * Method used to search a list of crops by an interval of harvest dates.
   */
  @GetMapping("/search")
  public List<CropDto> getCropsByHarvestDateInterval(
      @RequestParam LocalDate start,
      @RequestParam LocalDate end
  ) {

    return cropService.getCropsByHarvestDateInterval(start, end).stream()
        .map(CropModelDtoConverter::cropToCropDto)
        .collect(Collectors.toList());
  }

  /**
   * Method used to associate - N:N relationship - a crop and a fertilizer.
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> associateCropAndFertilizer(
      @PathVariable Long cropId,
      @PathVariable Long fertilizerId
  ) {
    Crop crop = cropService.associateCropAndFertilizer(cropId, fertilizerId);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   * Method used to list all fertilizers in a crop.
   */
  @GetMapping("/{cropId}/fertilizers")
  public List<FertilizerDto> listFertilizersInCrop(@PathVariable Long cropId) {
    return cropService.listFertilizersInCrop(cropId).stream()
        .map(FertilizerModelDtoConverter::fertilizerToFertilizerDto)
        .collect(Collectors.toList());
  }

}
