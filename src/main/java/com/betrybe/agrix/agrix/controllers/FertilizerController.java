package com.betrybe.agrix.agrix.controllers;

import static com.betrybe.agrix.agrix.util.FertilizerModelDtoConverter.fertilizerCreationDtoToFertilizer;
import static com.betrybe.agrix.agrix.util.FertilizerModelDtoConverter.fertilizerToFertilizerDto;

import com.betrybe.agrix.agrix.controllers.dto.FertilizerCreationDto;
import com.betrybe.agrix.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.agrix.services.FertilizerService;
import com.betrybe.agrix.agrix.util.FertilizerModelDtoConverter;
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
 * Controller of Fertilizer.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Method used to create a fertilizer in the database.
   */
  @PostMapping()
  public ResponseEntity<FertilizerDto> createFertilizer(
      @RequestBody FertilizerCreationDto fertilizerCreationDto) {
    Fertilizer fertilizer = fertilizerService.insertFertilizer(
        fertilizerCreationDtoToFertilizer(fertilizerCreationDto));

    FertilizerDto fertilizerDto = fertilizerToFertilizerDto(fertilizer);

    return ResponseEntity.status(HttpStatus.CREATED).body(fertilizerDto);
  }

  /**
   * Method used to get all fertilizers from the database.
   */
  @GetMapping()
  //  @Secured({"ROLE_ADMIN"})
  @PreAuthorize("hasRole('ADMIN')")
  public List<FertilizerDto> getAllFertilizers() {
    return fertilizerService.getAllFertilizers().stream()
        .map(FertilizerModelDtoConverter::fertilizerToFertilizerDto)
        .collect(Collectors.toList());
  }

  /**
   * Method used to get fertilizer by id from the database.
   */
  @GetMapping("/{fertilizerId}")
  public ResponseEntity<FertilizerDto> getFertilizerById(@PathVariable Long fertilizerId) {
    Fertilizer fertilizer = fertilizerService.getFertilizerById(fertilizerId);
    return ResponseEntity.status(HttpStatus.OK).body(fertilizerToFertilizerDto(fertilizer));
  }
}
