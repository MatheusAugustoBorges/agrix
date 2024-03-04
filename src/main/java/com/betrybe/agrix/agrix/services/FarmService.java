package com.betrybe.agrix.agrix.services;

import com.betrybe.agrix.agrix.exceptions.FarmNotFoundException;
import com.betrybe.agrix.agrix.models.entities.Crop;
import com.betrybe.agrix.agrix.models.entities.Farm;
import com.betrybe.agrix.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.agrix.models.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is responsible for the business logic of the Farm entity.
 */
@Service
public class FarmService {

  private final FarmRepository farmRepository;
  private final CropRepository cropRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  /**
   * Method used to create a farm in the database.
   */
  public Farm insertFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * Method used to update a farm in the database.
   */
  public Farm updateFarm(Long id, Farm farm) {
    Optional<Farm> farmOptional = farmRepository.findById(id);
    if (farmOptional.isPresent()) {
      Farm farmFromDb = farmOptional.get();
      farmFromDb.setName(farm.getName());
      farmFromDb.setSize(farm.getSize());

      return farmRepository.save(farmFromDb);
    }
    throw new FarmNotFoundException();
  }

  /**
   * Method used to remove a farm in the database.
   */
  public Farm removeFarm(Long id) {
    Optional<Farm> farmOptional = farmRepository.findById(id);
    if (farmOptional.isPresent()) {
      farmRepository.deleteById(id);
    }
    throw new FarmNotFoundException();
  }

  /**
   * Method used to get a farm by its id.
   */
  public Farm getFarmById(Long id) {
    Optional<Farm> searchedFarmById = farmRepository.findById(id);
    if (searchedFarmById.isPresent()) {
      return searchedFarmById.get();
    }
    throw new FarmNotFoundException();
  }

  /**
   * Method used to get all farms from the database.
   */
  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  // ----------------------------------------------------------
  // Implementing methods to interact with CropRepository

  /**
   * Method used to post a crop in a farm.
   */
  public Crop postCropInFarm(Long farmId, Crop crop) {
    Optional<Farm> farmOptional = farmRepository.findById(farmId);
    if (farmOptional.isPresent()) {
      Farm farm = farmOptional.get();
      crop.setFarm(farm);
      return cropRepository.save(crop);
    }
    throw new FarmNotFoundException();
  }

  /**
   * Method used to get all crops of a farm.
   */
  public List<Crop> getAllCropsOfFarm(Long farmId) {
    Optional<Farm> farmOptional = farmRepository.findById(farmId);
    if (farmOptional.isPresent()) {
      return farmOptional.get().getCrops();
    }
    throw new FarmNotFoundException();
  }
}
