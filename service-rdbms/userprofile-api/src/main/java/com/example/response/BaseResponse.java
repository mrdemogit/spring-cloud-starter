package com.example.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class BaseResponse {

    private Long id;
    private Date createdDate;
    private Date modifiedDate;
}
