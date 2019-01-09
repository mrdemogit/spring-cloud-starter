package com.example.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@RequiredArgsConstructor
@Document(collection = "user_profile_stats")
public class UserProfileStats {

    @Id
    private String id;

    @NotNull
    private Date statsDate;

    @NotNull
    private Long userProfileId;
}
