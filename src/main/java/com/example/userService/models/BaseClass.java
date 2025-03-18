package com.example.userService.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @CreatedDate
    @Column(updatable = false)
    Date createdDate;

    @LastModifiedDate
    Date lastModifiedDate;

    Boolean deleted= false;
}
