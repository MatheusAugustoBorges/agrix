package com.betrybe.agrix.agrix.models.repositories;

import com.betrybe.agrix.agrix.models.entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Farm repository.
 */
@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {
}
