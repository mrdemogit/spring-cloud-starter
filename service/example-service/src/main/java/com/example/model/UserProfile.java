package com.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
public class UserProfile extends BaseEntity {

    private String firstName;
    private String lastName;
}
