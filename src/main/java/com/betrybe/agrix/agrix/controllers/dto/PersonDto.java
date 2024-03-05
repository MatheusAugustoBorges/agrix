package com.betrybe.agrix.agrix.controllers.dto;

import com.betrybe.agrix.agrix.security.Role;

/**
 * Person data transfer object.
 */
public record PersonDto(Long id, String username, Role role) {
}
