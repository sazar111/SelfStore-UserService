package com.example.userService.dtos;

import com.example.userService.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDto {
    private String email;
    private String password;
    private String name;

    public SignUpDto fromUserToSignUpDto(User user){
        this.email = user.getEmail();
        this.name = user.getName();
        return this;
    }
}
