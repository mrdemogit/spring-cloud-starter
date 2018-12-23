package com.example.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserProfileResponse extends BaseResponse {

    private String firstName;
    private String lastName;
    private String avatar;
}
