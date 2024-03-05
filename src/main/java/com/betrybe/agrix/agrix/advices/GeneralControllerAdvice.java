package com.betrybe.agrix.agrix.advices;

import com.betrybe.agrix.agrix.exceptions.CropNotFoundException;
import com.betrybe.agrix.agrix.exceptions.FarmNotFoundException;
import com.betrybe.agrix.agrix.exceptions.FertilizerNotFoundException;
import com.betrybe.agrix.agrix.exceptions.PersonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This class represents a GeneralControllerAdvice.
 */
@ControllerAdvice
public class GeneralControllerAdvice {

  /**
   * This method handles an exception for FarmNotFoundException.
   */
  @ExceptionHandler(FarmNotFoundException.class)
  public ResponseEntity<String> handleFarmNotFoundException(FarmNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
  }

  /**
   * This method handles an exception for CropNotFoundException.
   */
  @ExceptionHandler(CropNotFoundException.class)
  public ResponseEntity<String> handleCropNotFoundException(CropNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plantação não encontrada!");
  }

  /**
   * This method handles an exception for FertilizerNotFoundException.
   */
  @ExceptionHandler(FertilizerNotFoundException.class)
  public ResponseEntity<String> handleFertilizerNotFoundException(FertilizerNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fertilizante não encontrado!");
  }

  /**
   * This method handles an exception for PersonNotFoundException.
   */
  @ExceptionHandler(PersonNotFoundException.class)
  public ResponseEntity<String> handlePersonNotFoundException(PersonNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada!");
  }

}
