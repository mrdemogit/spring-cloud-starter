package com.example.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class UserProfileRequest {

    private String firstName;
    private String lastName;
}
