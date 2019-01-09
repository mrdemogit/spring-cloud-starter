package com.example.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class UserProfileStatsResponse {

    private String id;
    private Date statsDate;
    private Long userProfileId;
}
