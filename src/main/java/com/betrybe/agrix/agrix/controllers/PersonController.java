package com.betrybe.agrix.agrix.controllers;

import static com.betrybe.agrix.agrix.util.PersonModelDtoConverter.personCreationDtoToPerson;
import static com.betrybe.agrix.agrix.util.PersonModelDtoConverter.personToPersonDto;

import com.betrybe.agrix.agrix.controllers.dto.PersonCreationDto;
import com.betrybe.agrix.agrix.controllers.dto.PersonDto;
import com.betrybe.agrix.agrix.models.entities.Person;
import com.betrybe.agrix.agrix.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {

  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Method used to create a person in the database.
   */
  @PostMapping
  public ResponseEntity<PersonDto> createPerson(@RequestBody PersonCreationDto presonCreationDto) {
    Person person = personService.create(
        personCreationDtoToPerson(presonCreationDto)
    );

    PersonDto personDto = personToPersonDto(person);

    return ResponseEntity.status(HttpStatus.CREATED).body(personDto);
  }

}
