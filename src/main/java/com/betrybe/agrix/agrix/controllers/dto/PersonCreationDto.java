package com.betrybe.agrix.agrix.controllers.dto;

import com.betrybe.agrix.agrix.security.Role;

/**
 * Person data transfer object.
 */
public record PersonCreationDto(String username, String password, Role role) {
}
