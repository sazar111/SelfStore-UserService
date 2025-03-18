package com.example.userService.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.example.userService.models.Token}
 */
@Getter
@Setter
public class ValidateTokenRequestDto implements Serializable {
    String token;
}