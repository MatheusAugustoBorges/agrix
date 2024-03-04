package com.betrybe.agrix.agrix.services;

import com.betrybe.agrix.agrix.exceptions.FertilizerNotFoundException;
import com.betrybe.agrix.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.agrix.models.repositories.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is responsible for the business logic of the Fertilizer entity.
 */
@Service
public class FertilizerService {

  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  // ----------------------------------------------------------
  // Implementing methods to interact with FertilizerRepository

  /**
   * Method used to create a fertilizer in the database.
   */
  public Fertilizer insertFertilizer(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  /**
   * Method used to update a fertilizer in the database.
   */
  public Fertilizer updateFertilizer(Long id, Fertilizer fertilizer) {
    Optional<Fertilizer> fertilizerOptional = fertilizerRepository.findById(id);
    if (fertilizerOptional.isPresent()) {
      Fertilizer fertilizerFromDb = fertilizerOptional.get();
      fertilizerFromDb.setName(fertilizer.getName());
      fertilizerFromDb.setBrand(fertilizer.getBrand());
      fertilizerFromDb.setComposition(fertilizer.getComposition());

      return fertilizerRepository.save(fertilizerFromDb);
    }
    throw new FertilizerNotFoundException();
  }

  /**
   * Method used to remove a fertilizer in the database.
   */
  public Fertilizer removeFertilizer(Long id) {
    Optional<Fertilizer> fertilizerOptional = fertilizerRepository.findById(id);
    if (fertilizerOptional.isPresent()) {
      fertilizerRepository.deleteById(id);
    }
    throw new FertilizerNotFoundException();
  }

  /**
   * Method used to get a fertilizer by its id.
   */
  public Fertilizer getFertilizerById(Long id) {
    Optional<Fertilizer> searchedFertilizerById = fertilizerRepository.findById(id);
    if (searchedFertilizerById.isPresent()) {
      return searchedFertilizerById.get();
    }
    throw new FertilizerNotFoundException();
  }

  /**
   * Method used to get all fertilizer from the database.
   */
  public List<Fertilizer> getAllFertilizers() {
    return fertilizerRepository.findAll();
  }
}
