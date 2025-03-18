package com.example.userService.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
public class Token extends BaseClass{
    private String value;
    @ManyToOne
    private User user;
    private Date expiryDate;
}
