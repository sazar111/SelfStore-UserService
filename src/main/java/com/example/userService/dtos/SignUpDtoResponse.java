package com.example.userService.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * DTO for {@link com.example.userService.models.User}
 */

@Getter
@Setter
public class SignUpDtoResponse {
    UUID id;
    Date createdDate;
    String name;
    String email;
}