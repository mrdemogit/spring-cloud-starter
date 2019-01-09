package com.example;

import com.example.request.UserProfileRequest;
import com.example.response.UserProfileResponse;
import com.example.service.UserProfileService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebfluxApplicationTests {

    @Autowired
    private WebTestClient webClient;

    @Autowired
    UserProfileService userProfileService;

    @Test
    public void shouldGetUserProfileById_returnUserProfileResponse() {
        webClient.get().uri("/userprofiles/{id}", 1)
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
    public void shouldGetUserProfileByRandom_returnUserProfileResponse() {
        webClient.get().uri("/userprofiles/random")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserProfileResponse.class)
                .consumeWith(response -> {
                            Assertions.assertThat(response.getResponseBody()).isNotNull();
                        }
                );
    }

    @Test
    public void shouldCreateUserProfile_returnUserProfileResponse() {
        UserProfileRequest userProfileRequest = new UserProfileRequest();
        userProfileRequest.setFirstName("FirstName");
        userProfileRequest.setLastName("LastName");

        webClient.post().uri("/userprofiles")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(userProfileRequest), UserProfileRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserProfileResponse.class)
                .consumeWith(response -> {
                            Assertions.assertThat(response.getResponseBody().getFirstName()).isEqualTo("FirstName");
                            Assertions.assertThat(response.getResponseBody().getLastName()).isEqualTo("LastName");
                        }
                );
    }
}
