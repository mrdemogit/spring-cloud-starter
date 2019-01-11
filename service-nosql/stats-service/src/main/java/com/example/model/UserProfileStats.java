package com.example.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@RequiredArgsConstructor
@Document
public class UserProfileStats {


    @Id
    private String id;

    @NotNull
    private Long userProfileId;

    @NotNull
    @Indexed
    private Date date;

    private String device;

    private String location;

}
