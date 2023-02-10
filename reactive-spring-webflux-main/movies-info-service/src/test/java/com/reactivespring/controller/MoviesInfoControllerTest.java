package intg.com.reactivespring.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.reactivespring.domain.MovieInfo;
import com.reactivespring.repository.MovieInfoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.test.StepVerifier;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class MoviesInfoControllerTest {

    public static final String MOVIE_INFO_URL = "/v1/movieinfos";
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    MovieInfoRepository movieInfoRepository;

    @BeforeEach
    void setUp() {
        var movieInfos = List.of(
                new MovieInfo(null, "The Dark Knight",
                        2008, List.of("Christian Bale", "HeathLedger"), LocalDate.parse("2008-07-18")),
                new MovieInfo("ABC", "Dark Night Rises",
                        2012, List.of("Christian Bale", "Tom Hardy"), LocalDate.parse("2012-07-28"))
        );
        movieInfoRepository.saveAll(movieInfos)
                .blockLast();
    }

    @AfterEach
    void tearDown() {
        movieInfoRepository.deleteAll().block();
    }

    @Test
    void addMovieInfo() {
        //given
        var movieInfo = new MovieInfo(null, "Batman Begins",
                2005, List.of("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15"));
        //when //then
        webTestClient.post()
                .uri(MOVIE_INFO_URL)
                .bodyValue(movieInfo)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(MovieInfo.class)
                .consumeWith(movieInfoEntityExchangeResult -> {
                    var savedMovieInfo = movieInfoEntityExchangeResult.getResponseBody();
                    assertNotNull(Objects.requireNonNull(savedMovieInfo).getMovieInfoId());
                });
    }

    @Test
    void getAllMovieInfos() {
        //given

        //when //then
        webTestClient.get()
                .uri(MOVIE_INFO_URL)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(MovieInfo.class)
                .hasSize(2);
    }

    @Test
    void getMovieInfoById() {
        //given

        webTestClient.get()
                .uri(MOVIE_INFO_URL + "/ABC")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Dark Night Rises");
    }

    @Test
    void getMovieInfoByYear() {
        //given

        var movieInfos = webTestClient.get()
                .uri(UriComponentsBuilder.fromUriString(MOVIE_INFO_URL)
                        .queryParam("year", 2008)
                        .buildAndExpand().toUri())
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(MovieInfo.class)
                .getResponseBody();

        StepVerifier.create(movieInfos)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void updateMovieInfo() {
        //given
        var movieInfo = new MovieInfo("ABC", "Dark Night Rises 1",
                2012, List.of("Christian Bale", "Tom Hardy"), LocalDate.parse("2012-07-28"));
        //when //then
        webTestClient.put()
                .uri(MOVIE_INFO_URL + "/ABC")
                .bodyValue(movieInfo)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(MovieInfo.class)
                .consumeWith(movieInfoEntityExchangeResult -> {
                    var updatedMovieInfo = movieInfoEntityExchangeResult.getResponseBody();
                    assertNotNull(updatedMovieInfo);
                    assertEquals("Dark Night Rises 1", updatedMovieInfo.getName());

                });
    }

    @Test
    void updateMovieInfoNotFound() {
        //given
        var movieInfo = new MovieInfo("ABC", "Dark Night Rises 1",
                2012, List.of("Christian Bale", "Tom Hardy"), LocalDate.parse("2012-07-28"));
        //when //then
        webTestClient.put()
                .uri(MOVIE_INFO_URL + "/DEF")
                .bodyValue(movieInfo)
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    void deleteMovieInfo() {
        //given

        //when //then
        webTestClient.delete()
                .uri(MOVIE_INFO_URL + "/ABC")
                .exchange()
                .expectStatus()
                .isNoContent();
    }
}