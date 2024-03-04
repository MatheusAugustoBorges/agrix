package com.betrybe.agrix.agrix.services;

import com.betrybe.agrix.agrix.exceptions.CropNotFoundException;
import com.betrybe.agrix.agrix.models.entities.Crop;
import com.betrybe.agrix.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.agrix.models.repositories.CropRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is responsible for the business logic of the Crop entity.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;

  private final FertilizerService fertilizerService;

  @Autowired
  public CropService(CropRepository cropRepository,
      FertilizerService fertilizerService) {
    this.cropRepository = cropRepository;
    this.fertilizerService = fertilizerService;
  }

  // ----------------------------------------------------------
  // Implementing methods to interact with CropRepository

  /**
   * Method used to create a crop in the database.
   */
  public Crop insertCrop(Crop crop) {
    return cropRepository.save(crop);
  }

  /**
   * Method used to update a crop in the database.
   */
  public Crop updateCrop(Long id, Crop crop) {
    Optional<Crop> cropOptional = cropRepository.findById(id);
    if (cropOptional.isPresent()) {
      Crop cropFromDb = cropOptional.get();
      cropFromDb.setName(crop.getName());
      cropFromDb.setPlantedArea(crop.getPlantedArea());

      return cropRepository.save(cropFromDb);
    }
    throw new CropNotFoundException();
  }

  /**
   * Method used to remove a crop in the database.
   */
  public Crop removeCrop(Long id) {
    Optional<Crop> cropOptional = cropRepository.findById(id);
    if (cropOptional.isPresent()) {
      cropRepository.deleteById(id);
    }
    throw new CropNotFoundException();
  }

  /**
   * Method used to get a crop by its id.
   */
  public Crop getCropById(Long id) {
    Optional<Crop> searchedCropById = cropRepository.findById(id);
    if (searchedCropById.isPresent()) {
      return searchedCropById.get();
    }
    throw new CropNotFoundException();
  }

  /**
   * Method used to get all crops from the database.
   */
  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  /**
   * Method used to search a list of crops by an interval of harvest dates.
   */
  public List<Crop> getCropsByHarvestDateInterval(LocalDate start, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(start, end);
  }

  /**
   * Method used to associate - N:N relationship - a crop and a fertilizer.
   */
  // Importante lembrar que no método a seguir foi usado o método getFertilizerById
  // da classe FertilizerService, e não o método findById da classe FertilizerRepository,
  // pois o método getFertilizerById da classe FertilizerService já lança uma exceção!!!
  // Se usasse o FertilizerRepository, teria que lançar a exceção FertilizerNotFoundException
  // novamente aqui, sendo que já está feita na FertilizerService.
  public Crop associateCropAndFertilizer(Long cropId, Long fertilizerId) {
    Optional<Crop> cropOptional = cropRepository.findById(cropId);
    if (cropOptional.isPresent()) {
      Crop crop = cropOptional.get();
      crop.getFertilizers().add(fertilizerService.getFertilizerById(fertilizerId));
      return cropRepository.save(crop);
    }
    throw new CropNotFoundException();
  }

  /**
   * Method used to list all fertilizers in a crop.
   */
  public List<Fertilizer> listFertilizersInCrop(Long cropId) {
    Optional<Crop> cropOptional = cropRepository.findById(cropId);
    if (cropOptional.isPresent()) {
      Crop crop = cropOptional.get();
      return crop.getFertilizers();
    }
    throw new CropNotFoundException();
  }

}
