package com.betrybe.agrix.agrix.models.repositories;

import com.betrybe.agrix.agrix.models.entities.Crop;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Crop repository.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

  public List<Crop> findByHarvestDateBetween(LocalDate start, LocalDate end);

}
