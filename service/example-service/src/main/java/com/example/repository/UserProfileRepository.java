package com.example.repository;

import com.example.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    List<UserProfile> findByLastNameAllIgnoringCase(String lastName);

}
