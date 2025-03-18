package com.example.userService.models;

import com.example.userService.dtos.SignUpDto;
import com.example.userService.dtos.SignUpDtoResponse;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User extends BaseClass {
    String name;
    private String email;
    private String hashedPassword;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
    private Boolean isEmailVerified;

    public SignUpDtoResponse toSignUpDtoResponse() {
        SignUpDtoResponse signUpDto = new SignUpDtoResponse();
        signUpDto.setName(name);
        signUpDto.setEmail(email);
        signUpDto.setId(id);
        signUpDto.setCreatedDate(createdDate);
        return signUpDto;
    }
}
