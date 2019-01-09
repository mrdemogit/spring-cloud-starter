package com.example.service;

import com.example.model.UserProfile;
import com.example.repository.UserProfileRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserProfileServiceTest {

    @Autowired
    private UserProfileService userProfileService;

    @Mock
    private UserProfileRepository userProfileRepository;

    @Test
    public void testFindById_shouldReturnUserProfile(){
        UserProfile userProfile = new UserProfile();
        userProfile.setId(1L);
        userProfile.setFirstName("FirstName");
        userProfile.setLastName("LastName");

        Mockito.when(userProfileRepository.findById(1L)).thenReturn(Optional.of(userProfile));

        userProfileService.findById(1L).subscribe(response -> {
            Assertions.assertThat(response.getFirstName()).isEqualTo("FirstName");
            Assertions.assertThat(response.getLastName()).isEqualTo("LastName");
        });
    }

    @Test
    public void testFindByRandom_shouldReturnUserProfile(){
        UserProfile userProfile = new UserProfile();
        userProfile.setId(1L);
        userProfile.setFirstName("FirstName");
        userProfile.setLastName("LastName");

        Mockito.when(userProfileRepository.count()).thenReturn(12L);
        Mockito.when(userProfileRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(userProfile));

        userProfileService.findByRandom().subscribe(response -> {
            Assertions.assertThat(response.getFirstName()).isEqualTo("FirstName");
            Assertions.assertThat(response.getLastName()).isEqualTo("LastName");
        });
    }

    @Test
    public void testCreateUserProfile_shouldReturnUserProfile(){
        UserProfile userProfile = new UserProfile();
        userProfile.setId(1L);
        userProfile.setFirstName("FirstName");
        userProfile.setLastName("LastName");

        Mockito.when(userProfileRepository.save(userProfile)).thenReturn(userProfile);

        userProfileService.create("FirstName", "LastName").subscribe(response -> {
            Assertions.assertThat(response.getFirstName()).isEqualTo("FirstName");
            Assertions.assertThat(response.getLastName()).isEqualTo("LastName");
        });
    }
}
