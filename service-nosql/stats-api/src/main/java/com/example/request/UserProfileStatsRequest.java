package com.example.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
@Setter
public class UserProfileStatsRequest {

    private Long userProfileId;
    private Date date;

}
