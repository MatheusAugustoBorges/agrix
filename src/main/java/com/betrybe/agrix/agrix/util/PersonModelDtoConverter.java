package com.betrybe.agrix.agrix.util;

import com.betrybe.agrix.agrix.controllers.dto.PersonCreationDto;
import com.betrybe.agrix.agrix.controllers.dto.PersonDto;
import com.betrybe.agrix.agrix.models.entities.Person;

/**
 * Utility farm class to convert between model and dto.
 */
public class PersonModelDtoConverter {

  /**
   * Convert from model to dto.
   */
  public static PersonDto personToPersonDto(Person person) {
    return new PersonDto(
        person.getId(),
        person.getUsername(),
        person.getRole()
    );
  }

  /**
   * Convert from dto to model.
   */
  public static Person personDtoToPerson(PersonDto personDto) {
    Person person = new Person();

    person.setId(personDto.id());
    person.setUsername(personDto.username());
    person.setRole(personDto.role());

    return person;
  }

  /**
   * Convert from creation dto to model.
   */
  public static Person personCreationDtoToPerson(PersonCreationDto personCreationDto) {
    Person person = new Person();

    person.setUsername(personCreationDto.username());
    person.setPassword(personCreationDto.password());
    person.setRole(personCreationDto.role());

    return person;
  }

}
