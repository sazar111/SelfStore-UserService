package com.example.userService.dtos;

import com.example.userService.models.Token;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.example.userService.models.Token}
 */
@Getter
@Setter
public class LoginResponseDto implements Serializable {
    Date createdDate;
    String value;
    Date expiryDate;
    String email;

    public LoginResponseDto fromToken(Token token){
        this.value=token.getValue();
        this.expiryDate=token.getExpiryDate();
        this.createdDate=token.getCreatedDate();
        this.email= token.getUser().getEmail();
        return this;
    }
}