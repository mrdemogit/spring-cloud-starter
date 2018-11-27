package com.example.model;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class UserProfile extends BaseEntity {

    private String firstName;
    private String lastName;
}
