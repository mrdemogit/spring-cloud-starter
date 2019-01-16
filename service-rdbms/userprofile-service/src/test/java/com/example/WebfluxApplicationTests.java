package com.example;

import com.example.producer.UserProfileProducer;
import com.example.response.UserProfileResponse;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebfluxApplicationTests {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    UserProfileProducer userProfileProducer;

    @Test
    public void testGetUserProfileById_shouldReturnUserProfileResponse() {
        webClient.get().uri("/api/userprofiles/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserProfileResponse.class)
                .consumeWith(response -> {
                            Assertions.assertThat(response.getResponseBody().getFirstName()).isEqualTo("George");
                            Assertions.assertThat(response.getResponseBody().getLastName()).isEqualTo("Bluth");
                        }
                );
    }

    @Test
    public void testGetUserProfileByRandom_shouldReturnUserProfileResponse() {
        webClient.get().uri("/api/userprofiles/random")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserProfileResponse.class)
                .consumeWith(response ->
                        Assertions.assertThat(response.getResponseBody()).isNotNull()
                );
    }

//    @Test
//    public void testCreateUserProfile_shouldReturnUserProfileResponse() {
//        UserProfileRequest userProfileRequest = new UserProfileRequest();
//        userProfileRequest.setFirstName("FirstName");
//        userProfileRequest.setLastName("LastName");
//
//        webClient.post().uri("/api/userprofiles")
//                .accept(MediaType.APPLICATION_JSON)
//                .body(Mono.just(userProfileRequest), UserProfileRequest.class)
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody(UserProfileResponse.class)
//                .consumeWith(response -> {
//                            Assertions.assertThat(response.getResponseBody().getFirstName()).isEqualTo("FirstName");
//                            Assertions.assertThat(response.getResponseBody().getLastName()).isEqualTo("LastName");
//                        }
//                );
//    }
}
